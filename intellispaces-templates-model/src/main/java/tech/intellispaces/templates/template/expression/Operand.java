package tech.intellispaces.templates.template.expression;

/**
 * Expression operand.
 */
public interface Operand {

  boolean isLiteral();

  boolean isVariable();

  Literal asLiteral();

  Variable asVariable();
}
