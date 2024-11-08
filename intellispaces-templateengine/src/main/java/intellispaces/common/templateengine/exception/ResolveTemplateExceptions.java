package intellispaces.common.templateengine.exception;

import intellispaces.common.base.text.StringFunctions;

/**
 * Provider of the exception {@link ResolveTemplateException}.
 */
public interface ResolveTemplateExceptions {

  static ResolveTemplateException withMessage(String message) {
    return new ResolveTemplateException(message);
  }

  static ResolveTemplateException withCauseAndMessage(Throwable cause, String message) {
    return new ResolveTemplateException(message, cause);
  }

  static ResolveTemplateException withMessage(String template, Object... params) {
    return new ResolveTemplateException(StringFunctions.resolveTemplate(template, params));
  }

  static ResolveTemplateException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new ResolveTemplateException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
