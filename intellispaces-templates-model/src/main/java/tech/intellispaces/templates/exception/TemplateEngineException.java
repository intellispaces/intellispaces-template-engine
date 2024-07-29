package tech.intellispaces.templates.exception;

import tech.intellispaces.commons.exception.PossibleViolationException;

public class TemplateEngineException extends PossibleViolationException {

  protected TemplateEngineException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected TemplateEngineException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
