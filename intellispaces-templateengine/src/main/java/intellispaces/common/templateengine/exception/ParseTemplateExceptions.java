package intellispaces.common.templateengine.exception;

import intellispaces.common.base.text.StringFunctions;

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

  static ParseTemplateException withCauseAndMessage(Throwable cause, String message) {
    return new ParseTemplateException(message, cause);
  }

  static ParseTemplateException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new ParseTemplateException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
