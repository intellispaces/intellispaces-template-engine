package intellispaces.templateengine.exception;

public class InvalidExpressionException extends ParseTemplateException {
  private final String expression;
  private final int offset;

  public InvalidExpressionException(final String expression, final int offset, final String messageTemplate, final Object... arguments) {
    super("Invalid expression: " + expression + " at offset " + offset + ". " + messageTemplate, arguments);
    this.expression = expression;
    this.offset = offset;
  }

  public InvalidExpressionException(final String expression, final int offset) {
    super("Invalid expression: " + expression + " at " + offset);
    this.expression = expression;
    this.offset = offset;
  }

  public String expression() {
    return expression;
  }

  public int offset() {
    return offset;
  }
}
