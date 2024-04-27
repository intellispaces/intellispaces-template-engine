package tech.intellispacesframework.templateengine.template.expression.compilation;

import tech.intellispacesframework.commons.exception.PossibleViolationException;
import tech.intellispacesframework.templateengine.template.expression.CompiledExpression;
import tech.intellispacesframework.templateengine.exception.ParseTemplateException;
import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.compilation.impl.CompiledFileObject;
import tech.intellispacesframework.templateengine.template.expression.compilation.impl.ExpressionClassLoader;
import tech.intellispacesframework.templateengine.template.expression.compilation.impl.ExpressionJavaFileManager;
import tech.intellispacesframework.templateengine.template.expression.compilation.impl.SourceFileObject;
import tech.intellispacesframework.templateengine.template.expression.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.util.UUID;
import java.util.stream.Stream;

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
    JavaCompiler.CompilationTask compilerTask = COMPILER.getTask(null, fileManager, diagnosticListener, compileOptions, null, List.of(sourceFileObject));
    if (!compilerTask.call()) {
      throw ParseTemplateException.withMessage("Failed to compile expression: {}", statement);
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
    String classpath = Stream.of(System.getProperty("java.class.path"), getCurrentJarPath(), getSupportJarPath())
        .filter(s -> s != null && !s.isEmpty())
        .reduce("", (s1, s2) -> s1.endsWith(File.pathSeparator) ? s1 + s2 : s1 + File.pathSeparator + s2);
    LOG.debug("Text template compiler classpath: " + classpath);
    return List.of("-classpath", classpath);
  }

  private static String getCurrentJarPath() {
    return getJarPath(CompileFunctions.class);
  }

  private static String getSupportJarPath() {
    return getJarPath(PossibleViolationException.class);
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
    @Override
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

      System.out.println();

      //todo: store diagnostic message
    }
  }
}
