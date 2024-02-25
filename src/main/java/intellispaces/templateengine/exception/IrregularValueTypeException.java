package intellispaces.templateengine.exception;

public class IrregularValueTypeException extends ResolveTemplateException {

  public IrregularValueTypeException(String messageTemplate, Object... arguments) {
    super(messageTemplate, arguments);
  }

  public IrregularValueTypeException(Throwable cause, String messageTemplate, Object... arguments) {
    super(cause, messageTemplate, arguments);
  }
}
