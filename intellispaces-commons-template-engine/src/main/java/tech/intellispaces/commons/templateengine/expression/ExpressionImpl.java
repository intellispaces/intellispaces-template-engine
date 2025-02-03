package tech.intellispaces.commons.templateengine.expression;

import java.util.List;

class ExpressionImpl implements Expression {
  private final String statement;
  private final String preparedStatement;
  private final CompiledExpression compiledExpression;
  private final List<Operand> operand;

  ExpressionImpl(
      String statement, String preparedStatement, CompiledExpression compiledExpression, List<Operand> operand
  ) {
    this.statement = statement;
    this.preparedStatement = preparedStatement;
    this.compiledExpression = compiledExpression;
    this.operand = operand;
  }

  @Override
  public String statement() {
    return statement;
  }

  @Override
  public String preparedStatement() {
    return preparedStatement;
  }

  @Override
  public CompiledExpression compiledExpression() {
    return compiledExpression;
  }

  @Override
  public List<Operand> operands() {
    return operand;
  }
}
