package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.expression.Expression;

import java.util.Objects;

public final class MarkerElseBuilder {
  private TextPosition position;
  private Expression condition;

  public static MarkerElseBuilder get() {
    return new MarkerElseBuilder();
  }

  public MarkerElseBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerElseBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerElse build() {
    validate();
    return new MarkerElseObject(position, condition);
  }

  private void validate() {
    Objects.requireNonNull(position);
  }

  private MarkerElseBuilder() {}
}
