package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.model.expression.Literal;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

public final class LiteralBuilder {
  private Value value;

  public static LiteralBuilder get() {
    return new LiteralBuilder();
  }

  public LiteralBuilder value(Value value) {
    this.value = value;
    return this;
  }

  public Literal build() {
    validate();
    return new LiteralObject(value);
  }

  private void validate() {
    Objects.requireNonNull(value);
  }

  private LiteralBuilder() {}
}
