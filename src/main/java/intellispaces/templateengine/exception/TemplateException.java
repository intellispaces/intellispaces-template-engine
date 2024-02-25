package intellispaces.templateengine.exception;

import intellispaces.commons.exception.ExpectedViolationException;

public class TemplateException extends ExpectedViolationException {

  public TemplateException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public TemplateException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
