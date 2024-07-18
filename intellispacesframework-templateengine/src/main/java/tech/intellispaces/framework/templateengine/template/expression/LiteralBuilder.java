package tech.intellispaces.framework.templateengine.template.expression;

import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Objects;

public final class LiteralBuilder {
  private Value value;

  LiteralBuilder() {}

  public LiteralBuilder value(Value value) {
    this.value = value;
    return this;
  }

  public Literal get() {
    validate();
    return new LiteralImpl(value);
  }

  private void validate() {
    Objects.requireNonNull(value);
  }
}
