package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.function.compile.CompiledStatement;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.expression.Operand;

import java.util.List;

final class ExpressionObject implements Expression {
  private final String statement;
  private final String preparedStatement;
  private final CompiledStatement compiledStatement;
  private final List<Operand> operand;

  ExpressionObject(String statement, String preparedStatement, CompiledStatement compiledStatement, List<Operand> operand) {
    this.statement = statement;
    this.preparedStatement = preparedStatement;
    this.compiledStatement = compiledStatement;
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
  public CompiledStatement compiledExpression() {
    return compiledStatement;
  }

  @Override
  public List<Operand> operands() {
    return operand;
  }
}
