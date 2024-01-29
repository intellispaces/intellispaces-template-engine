package intellispaces.templateengine.exception;

import intellispaces.commons.exception.ExpectedViolationException;

public class ParseTemplateException extends ExpectedViolationException {

  public ParseTemplateException(final String messageTemplate, final Object... arguments) {
    super(messageTemplate, arguments);
  }

  public ParseTemplateException(final Throwable cause, final String messageTemplate, final Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
