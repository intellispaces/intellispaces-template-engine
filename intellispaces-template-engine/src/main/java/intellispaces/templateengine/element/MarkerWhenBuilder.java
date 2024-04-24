package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.position.Position;

import java.util.Objects;

public final class MarkerWhenBuilder {
  private Position position;
  private Expression condition;

  public static MarkerWhenBuilder get() {
    return new MarkerWhenBuilder();
  }

  public MarkerWhenBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public MarkerWhenBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerWhen build() {
    validate();
    return new MarkerWhenImpl(position, condition);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(condition);
  }

  private MarkerWhenBuilder() {}
}
