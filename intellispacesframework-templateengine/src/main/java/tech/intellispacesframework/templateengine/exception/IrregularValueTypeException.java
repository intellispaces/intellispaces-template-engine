package tech.intellispacesframework.templateengine.exception;

public class IrregularValueTypeException extends ResolveTemplateException {

  protected IrregularValueTypeException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public static IrregularValueTypeException withMessage(String messageTemplate, Object... arguments) {
    return new IrregularValueTypeException(messageTemplate, arguments);
  }
}
