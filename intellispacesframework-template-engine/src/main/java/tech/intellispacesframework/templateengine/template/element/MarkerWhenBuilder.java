package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.source.position.Position;

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
