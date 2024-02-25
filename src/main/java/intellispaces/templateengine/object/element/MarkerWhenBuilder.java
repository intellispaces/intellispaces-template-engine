package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerWhen;
import intellispaces.templateengine.model.expression.Expression;

import java.util.Objects;

public final class MarkerWhenBuilder {
  private TextPosition position;
  private Expression condition;

  public static MarkerWhenBuilder get() {
    return new MarkerWhenBuilder();
  }

  public MarkerWhenBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerWhenBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerWhen build() {
    validate();
    return new MarkerWhenObject(position, condition);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(condition);
  }

  private MarkerWhenBuilder() {}
}
