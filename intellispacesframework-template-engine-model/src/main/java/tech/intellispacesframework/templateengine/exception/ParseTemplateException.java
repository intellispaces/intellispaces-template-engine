package tech.intellispacesframework.templateengine.exception;

public class ParseTemplateException extends TemplateEngineException {

  protected ParseTemplateException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected ParseTemplateException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }

  public static ParseTemplateException withMessage(String messageTemplate, Object... arguments) {
    return new ParseTemplateException(messageTemplate, arguments);
  }

  public static ParseTemplateException withCauseAndMessage(Throwable cause, String messageTemplate, Object... arguments) {
    return new ParseTemplateException(cause, messageTemplate, arguments);
  }
}
