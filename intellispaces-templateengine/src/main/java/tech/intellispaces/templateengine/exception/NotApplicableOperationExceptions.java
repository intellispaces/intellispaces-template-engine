package tech.intellispaces.templateengine.exception;

import tech.intellispaces.commons.text.StringFunctions;

/**
 * Provider of the exception {@link NotApplicableOperationException}.
 */
public interface NotApplicableOperationExceptions {

  static NotApplicableOperationException withMessage(String message) {
    return new NotApplicableOperationException(message);
  }

  static NotApplicableOperationException withMessage(String template, Object... params) {
    return new NotApplicableOperationException(StringFunctions.resolveTemplate(template, params));
  }
}
