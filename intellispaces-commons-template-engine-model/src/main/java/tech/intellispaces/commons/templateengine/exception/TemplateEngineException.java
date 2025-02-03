package tech.intellispaces.commons.templateengine.exception;

import tech.intellispaces.commons.base.exception.CheckedException;

public class TemplateEngineException extends CheckedException {

  public TemplateEngineException(String message) {
    super(message);
  }

  public TemplateEngineException(String message, Exception cause) {
    super(message, cause);
  }
}
