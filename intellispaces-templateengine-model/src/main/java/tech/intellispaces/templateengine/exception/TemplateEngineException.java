package tech.intellispaces.templateengine.exception;


import tech.intellispaces.entity.exception.AssumptionViolationException;

public class TemplateEngineException extends AssumptionViolationException {

  public TemplateEngineException(String message) {
    super(message);
  }

  public TemplateEngineException(String message, Exception cause) {
    super(message, cause);
  }
}
