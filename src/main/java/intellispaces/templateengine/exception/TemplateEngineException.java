package intellispaces.templateengine.exception;

import intellispaces.commons.exception.PossibleViolationException;

public class TemplateEngineException extends PossibleViolationException {

  public TemplateEngineException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public TemplateEngineException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
