package tech.intellispaces.templateengine.exception;

import tech.intellispaces.commons.exception.CheckedException;

public class TemplateEngineException extends CheckedException {

  public TemplateEngineException(String message) {
    super(message);
  }

  public TemplateEngineException(String message, Exception cause) {
    super(message, cause);
  }
}
