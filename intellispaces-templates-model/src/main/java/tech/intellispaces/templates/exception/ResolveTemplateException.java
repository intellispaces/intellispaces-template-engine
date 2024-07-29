package tech.intellispaces.templates.exception;

public class ResolveTemplateException extends TemplateEngineException {

  protected ResolveTemplateException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected ResolveTemplateException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }

  public static ResolveTemplateException withMessage(String messageTemplate, Object... arguments) {
    return new ResolveTemplateException(messageTemplate, arguments);
  }

  public static ResolveTemplateException withCauseAndMessage(Throwable cause, String messageTemplate, Object... arguments) {
    return new ResolveTemplateException(cause, messageTemplate, arguments);
  }
}
