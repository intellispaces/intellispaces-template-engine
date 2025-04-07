package tech.intellispaces.templateengine.exception;

import tech.intellispaces.commons.text.StringFunctions;

/**
 * Provider of the exception {@link ParseTemplateException}.
 */
public interface ParseTemplateExceptions {

  static ParseTemplateException withMessage(String message) {
    return new ParseTemplateException(message);
  }

  static ParseTemplateException withMessage(String template, Object... params) {
    return new ParseTemplateException(StringFunctions.resolveTemplate(template, params));
  }

  static ParseTemplateException withCauseAndMessage(Exception cause, String message) {
    return new ParseTemplateException(message, cause);
  }

  static ParseTemplateException withCauseAndMessage(
      Exception cause, String template, Object... params
  ) {
    return new ParseTemplateException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
