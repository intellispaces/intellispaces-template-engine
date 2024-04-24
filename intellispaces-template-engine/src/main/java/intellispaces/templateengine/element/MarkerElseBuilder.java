package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.position.Position;

import java.util.Objects;

public final class MarkerElseBuilder {
  private Position position;
  private Expression condition;

  public static MarkerElseBuilder get() {
    return new MarkerElseBuilder();
  }

  public MarkerElseBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public MarkerElseBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerElse build() {
    validate();
    return new MarkerElseImpl(position, condition);
  }

  private void validate() {
    Objects.requireNonNull(position);
  }

  private MarkerElseBuilder() {}
}
