package tech.intellispaces.framework.templateengine.template.expression;

import tech.intellispaces.framework.templateengine.template.expression.value.Value;

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
