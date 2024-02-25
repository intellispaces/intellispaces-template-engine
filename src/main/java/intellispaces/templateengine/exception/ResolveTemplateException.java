package intellispaces.templateengine.exception;

public class ResolveTemplateException extends TemplateException {

  public ResolveTemplateException(final String messageTemplate, final Object... arguments) {
    super(messageTemplate, arguments);
  }

  public ResolveTemplateException(final Throwable cause, final String messageTemplate, final Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
