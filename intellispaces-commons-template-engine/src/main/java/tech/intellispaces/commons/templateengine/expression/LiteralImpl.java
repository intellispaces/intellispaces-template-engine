package tech.intellispaces.commons.templateengine.expression;

import tech.intellispaces.commons.templateengine.expression.value.Value;

class LiteralImpl implements Literal {
  private final Value value;

  LiteralImpl(Value value) {
    this.value = value;
  }

  @Override
  public Value value() {
    return value;
  }
}
