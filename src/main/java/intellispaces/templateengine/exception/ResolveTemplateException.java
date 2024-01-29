package intellispaces.templateengine.exception;

import intellispaces.commons.exception.ExpectedViolationException;

public class ResolveTemplateException extends ExpectedViolationException {

  public ResolveTemplateException(final String messageTemplate, final Object... arguments) {
    super(messageTemplate, arguments);
  }

  public ResolveTemplateException(final Throwable cause, final String messageTemplate, final Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
