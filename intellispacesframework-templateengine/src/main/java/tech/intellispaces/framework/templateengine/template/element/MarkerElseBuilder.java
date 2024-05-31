package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;

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
