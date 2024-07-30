package tech.intellispaces.templates.expression.compilation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.intellispaces.commons.exception.PossibleViolationException;
import tech.intellispaces.templates.exception.ParseTemplateException;
import tech.intellispaces.templates.exception.ResolveTemplateException;
import tech.intellispaces.templates.expression.CompiledExpression;
import tech.intellispaces.templates.expression.value.Value;
import tech.intellispaces.templates.template.Template;
import tech.intellispaces.templates.template.Templates;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Expression compilation functions.
 */
public final class CompileFunctions {
  private static final JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();
  private static final Logger LOG = LoggerFactory.getLogger(CompileFunctions.class);

  public static CompiledExpression compileExpression(String statement) throws ParseTemplateException {
    String className = "CompiledExpression" + UUID.randomUUID().toString().replace("-", "");
    String classSource = makeCompiledExpressionSource(className, statement);
    List<CompiledFileObject> fileObjects = compileExpressionClass(className, classSource, statement);
    return getCompiledExpression(className, fileObjects);
  }

  private static String makeCompiledExpressionSource(String className, String statement) {
    return
        "public class " + className + " implements " + CompiledExpression.class.getName() + " {\n" +
            "  public " + Value.class.getName() + " resolve(" + Value.class.getName() + "[] operands) throws " + ResolveTemplateException.class.getName() + " {\n" +
            "    return " + statement + ";\n" +
            "  }\n" +
            "}";
  }

  private static List<CompiledFileObject> compileExpressionClass(
      String className, String classSource, String statement
  ) throws ParseTemplateException {
    LOG.debug("Compile expression: " + statement);
    var sourceFileObject = new SourceFileObject(className, classSource);
    var fileManager = new ExpressionJavaFileManager(COMPILER.getStandardFileManager(null, null, null));
    List<String> compileOptions = makeCompileOptions();
    var diagnosticListener = new CompileDiagnosticListener();
    JavaCompiler.CompilationTask compilerTask = COMPILER.getTask(
        null, fileManager, diagnosticListener, compileOptions, null, List.of(sourceFileObject)
    );
    if (!compilerTask.call()) {
      throw ParseTemplateException.withMessage("Failed to compile expression: {}. Reason(s):\n{}",
          statement, diagnosticListener.getMessage());
    }
    return fileManager.getGeneratedOutputFiles();
  }

  private static CompiledExpression getCompiledExpression(
      String className, List<CompiledFileObject> fileObjects
  ) throws ParseTemplateException {
    var classLoader = new ExpressionClassLoader(fileObjects, CompileFunctions.class.getClassLoader());
    try {
      Class<?> aClass = classLoader.loadClass(className);
      return (CompiledExpression) aClass.getConstructor().newInstance();
    } catch (Throwable e) {
      throw ParseTemplateException.withCauseAndMessage(e, "Failed to process template expression");
    }
  }

  private static List<String> makeCompileOptions() {
    String classpath = getClassPaths().stream()
        .filter(p -> p != null && !p.isEmpty())
        .reduce("", (p1, p2) -> p1.endsWith(File.pathSeparator) ? p1 + p2 : p1 + File.pathSeparator + p2);
    LOG.debug("Template compiler classpath: " + classpath);
    return List.of("-classpath", classpath);
  }

  private static Set<String> getClassPaths() {
    return Set.of(
        System.getProperty("java.class.path"),
        getJarPath(Template.class),
        getJarPath(Templates.class),
        getJarPath(PossibleViolationException.class)
    );
  }

  private static String getJarPath(Class<?> classFromJar) {
    String jarPath = getJarPathByProtectionDomain(classFromJar);
    if (jarPath == null) {
      jarPath = getJarPathByResource(classFromJar);
    }
    return jarPath;
  }

  private static String getJarPathByProtectionDomain(Class<?> classFromJar) {
    try {
      CodeSource codeSource = classFromJar.getProtectionDomain().getCodeSource();
      if (codeSource != null) {
        return path(codeSource.getLocation());
      }
    } catch (Exception e) {
      // ignore
    }
    return null;
  }

  private static String getJarPathByResource(Class<?> classFromJar) {
    URL classResource = classFromJar.getResource(classFromJar.getSimpleName() + ".class");
    if (classResource != null) {
      String url = classResource.toString();
      if (url.startsWith("jar:file:")) {
        String path = url.replaceAll("^jar:(file:.*[.]jar)!/.*", "$1");
        try {
          return path(new URL(path));
        } catch (Exception e) {
          // ignore
        }
      }
    }
    return null;
  }

  private static String path(URL url) throws URISyntaxException {
    return Paths.get(url.toURI()).normalize().toString();
  }

  private CompileFunctions() {}

  private static final class CompileDiagnosticListener implements DiagnosticListener<JavaFileObject> {
    private int index = 1;
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
      sb.append(index)
          .append(". [").append(diagnostic.getKind().name()).append("] ")
          .append(diagnostic.getMessage(null))
          .append("\n");
      index++;
    }

    String getMessage() {
      return sb.toString();
    }
  }
}
