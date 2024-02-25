package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.model.expression.Literal;
import intellispaces.templateengine.model.value.Value;

final class LiteralObject implements Literal {
  private final Value value;

  LiteralObject(Value value) {
    this.value = value;
  }

  @Override
  public Value value() {
    return value;
  }
}
