package intellispaces.templateengine.expression;

import intellispaces.templateengine.expression.value.Value;

/**
 * Expression literal.
 */
public interface Literal extends Operand {

  @Override
  default boolean isLiteral() {
    return true;
  }

  @Override
  default boolean isVariable() {
    return false;
  }

  @Override
  default Literal asLiteral() {
    return this;
  }

  @Override
  default Variable asVariable() {
    return null;
  }

  /**
   * Literal value.
   */
  Value value();
}
