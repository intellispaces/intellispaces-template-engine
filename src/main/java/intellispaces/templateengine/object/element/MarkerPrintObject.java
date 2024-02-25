package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.expression.Expression;

final class MarkerPrintObject implements MarkerPrint {
  private final TextPosition position;
  private final Expression outputExpression;

  MarkerPrintObject(TextPosition position, Expression outputExpression) {
    this.position = position;
    this.outputExpression = outputExpression;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public Expression outputExpression() {
    return outputExpression;
  }

  @Override
  public String toString() {
    return "{{print " + outputExpression.statement() + "}}";
  }
}
