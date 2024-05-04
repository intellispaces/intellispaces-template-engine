package tech.intellispacesframework.templateengine.exception;

import tech.intellispacesframework.commons.exception.PossibleViolationException;

public class TemplateEngineException extends PossibleViolationException {

  protected TemplateEngineException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  protected TemplateEngineException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
