package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.expression.Expression;

final class MarkerSetObject implements MarkerSet {
  private final TextPosition position;
  private final String valueName;
  private final Expression valueExpression;

  MarkerSetObject(TextPosition position, String valueName, Expression valueExpression) {
    this.position = position;
    this.valueName = valueName;
    this.valueExpression = valueExpression;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public String valueName() {
    return valueName;
  }

  @Override
  public Expression valueExpression() {
    return valueExpression;
  }

  @Override
  public String toString() {
    return "{{set " + valueName + " = " + valueExpression.statement() + "}}";
  }
}
