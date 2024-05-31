package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;

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
