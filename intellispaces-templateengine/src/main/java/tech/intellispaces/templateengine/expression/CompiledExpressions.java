package tech.intellispaces.templateengine.expression;

import tech.intellispaces.commons.compilation.CompilationFunctions;
import tech.intellispaces.commons.exception.CheckedException;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.commons.type.ClassFunctions;
import tech.intellispaces.templateengine.exception.ParseTemplateException;
import tech.intellispaces.templateengine.exception.ParseTemplateExceptions;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.value.Value;
import tech.intellispaces.templateengine.template.Template;
import tech.intellispaces.templateengine.template.Templates;

import java.util.Set;
import java.util.UUID;

/**
 * Compiled expressions provider.
 */
public final class CompiledExpressions {

  private CompiledExpressions() {}

  public static CompiledExpression compileExpression(String statement) throws ParseTemplateException {
    try {
      String className = "CompiledExpression" + StringFunctions.capitalizeFirstLetter(
          UUID.randomUUID().toString().replace("-", "")
      );
      return CompilationFunctions.createInstance(
          className,
          makeCompiledExpressionSource(className, statement),
          getClassPaths(),
          CompiledExpression.class
      );
    } catch (Exception e) {
      throw ParseTemplateExceptions.withCauseAndMessage(e, "Failed to compile expression {0}", statement);
    }
  }

  private static String makeCompiledExpressionSource(String className, String statement) {
    return "public class " + className + " implements " + CompiledExpression.class.getName() + ", " + ExpressionApiImpl.class.getName() + " {\n" +
            "  public " + Value.class.getName() + " resolve(" + Value.class.getName() + "[] operands) throws " + ResolveTemplateException.class.getName() + " {\n" +
            "    return " + statement + ";\n" +
            "  }\n" +
            "}";
  }

  private static Set<String> getClassPaths() {
    return Set.of(
        System.getProperty("java.class.path"),
        ClassFunctions.getJarPath(Template.class),
        ClassFunctions.getJarPath(Templates.class),
        ClassFunctions.getJarPath(CheckedException.class)
    );
  }
}
