package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerWhen;
import intellispaces.templateengine.model.expression.Expression;

final class MarkerWhenObject implements MarkerWhen {
  private final TextPosition position;
  private final Expression condition;

  MarkerWhenObject(TextPosition position, Expression condition) {
    this.position = position;
    this.condition = condition;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public Expression condition() {
    return condition;
  }

  @Override
  public String toString() {
    return "{{when " + condition.statement() + "}}";
  }
}
