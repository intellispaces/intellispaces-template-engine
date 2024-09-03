package intellispaces.common.templateengine.exception;

import intellispaces.common.exception.PossibleViolationException;

public class TemplateEngineException extends PossibleViolationException {

  protected TemplateEngineException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected TemplateEngineException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
