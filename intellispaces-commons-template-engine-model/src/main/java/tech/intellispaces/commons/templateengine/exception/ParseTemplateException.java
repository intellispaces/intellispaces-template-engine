package tech.intellispaces.commons.templateengine.exception;

public class ParseTemplateException extends TemplateEngineException {

  public ParseTemplateException(String message) {
    super(message);
  }

  public ParseTemplateException(String message, Exception cause) {
    super(message, cause);
  }
}
