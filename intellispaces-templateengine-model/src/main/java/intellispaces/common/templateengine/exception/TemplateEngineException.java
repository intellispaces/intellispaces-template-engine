package intellispaces.common.templateengine.exception;


import intellispaces.common.base.exception.AssumptionViolationException;

public class TemplateEngineException extends AssumptionViolationException {

  public TemplateEngineException(String message) {
    super(message);
  }

  public TemplateEngineException(String message, Throwable cause) {
    super(message, cause);
  }
}
