package intellispaces.templateengine.template.expression;

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
  CompiledExpression compiledExpression();

  /**
   * List of expression operands.
   */
  List<Operand> operands();
}
