package intellispaces.common.templateengine.expression;

/**
 * Expression operand.
 */
public interface Operand {

  boolean isLiteral();

  boolean isVariable();

  Literal asLiteral();

  Variable asVariable();
}
