package tech.intellispaces.templates.expression;

/**
 * Expression variable.
 */
public interface Variable extends Operand {

  @Override
  default boolean isLiteral() {
    return false;
  }

  @Override
  default boolean isVariable() {
    return true;
  }

  @Override
  default Literal asLiteral() {
    return null;
  }

  @Override
  default Variable asVariable() {
    return this;
  }

  /**
   * Variable name.
   */
  String name();
}
