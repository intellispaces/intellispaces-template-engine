package tech.intellispacesframework.templateengine.exception;

/**
 * Throws if operation is not applicable for current value.
 */
public class NotApplicableOperationException extends ResolveTemplateException {

  protected NotApplicableOperationException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public static NotApplicableOperationException withMessage(String messageTemplate, Object... arguments) {
    return new NotApplicableOperationException(messageTemplate, arguments);
  }
}
