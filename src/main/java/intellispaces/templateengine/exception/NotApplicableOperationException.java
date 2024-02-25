package intellispaces.templateengine.exception;

/**
 * Throws if operation is not applicable for current value.
 */
public class NotApplicableOperationException extends ResolveTemplateException {

  public NotApplicableOperationException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public NotApplicableOperationException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
