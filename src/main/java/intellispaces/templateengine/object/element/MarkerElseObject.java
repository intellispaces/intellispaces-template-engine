package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.expression.Expression;

final class MarkerElseObject implements MarkerElse {
  private final TextPosition position;
  private final Expression condition;

  MarkerElseObject(TextPosition position, Expression condition) {
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
    if (condition == null) {
      return "{{else}}";
    } else {
      return "{{else " + condition.statement() + "}}";
    }
  }
}
