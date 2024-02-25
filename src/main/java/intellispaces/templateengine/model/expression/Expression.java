package intellispaces.templateengine.model.expression;

import intellispaces.templateengine.function.compile.CompiledStatement;

import java.util.List;

/**
 * Parsed expression statement.
 */
public interface Expression {

  /**
   * Expression statement.
   */
  String statement();

  /**
   * Prepared statement.
   */
  String preparedStatement();

  /**
   * Compiled expression.
   */
  CompiledStatement compiledExpression();

  /**
   * List of expression operands.
   */
  List<Operand> operands();
}
