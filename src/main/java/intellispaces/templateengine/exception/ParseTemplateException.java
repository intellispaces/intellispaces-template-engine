package intellispaces.templateengine.exception;

public class ParseTemplateException extends TemplateException {

  public ParseTemplateException(final String messageTemplate, final Object... arguments) {
    super(messageTemplate, arguments);
  }

  public ParseTemplateException(final Throwable cause, final String messageTemplate, final Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
