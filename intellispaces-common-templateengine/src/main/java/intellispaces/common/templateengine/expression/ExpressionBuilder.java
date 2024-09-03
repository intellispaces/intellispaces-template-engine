package intellispaces.common.templateengine.expression;

import java.util.List;
import java.util.Objects;

public final class ExpressionBuilder {
  private String statement;
  private String preparedStatement;
  private CompiledExpression compiledExpression;
  private List<Operand> operands;

  ExpressionBuilder() {}

  public ExpressionBuilder statement(String statement) {
    this.statement = statement;
    return this;
  }

  public ExpressionBuilder preparedStatement(String preparedStatement) {
    this.preparedStatement = preparedStatement;
    return this;
  }

  public ExpressionBuilder compiledExpression(CompiledExpression compiledStatement) {
    this.compiledExpression = compiledStatement;
    return this;
  }

  public ExpressionBuilder operands(List<Operand> operands) {
    this.operands = operands;
    return this;
  }

  public Expression get() {
    validate();
    return new ExpressionImpl(statement, preparedStatement, compiledExpression, List.copyOf(operands));
  }

  private void validate() {
    Objects.requireNonNull(statement);
    Objects.requireNonNull(preparedStatement);
    Objects.requireNonNull(compiledExpression);
    Objects.requireNonNull(operands);
  }
}
