package intellispaces.common.templateengine.exception;

/**
 * Throws if operation is not applicable for current value.
 */
public class NotApplicableOperationException extends ResolveTemplateException {

  public NotApplicableOperationException(String message) {
    super(message);
  }
}
