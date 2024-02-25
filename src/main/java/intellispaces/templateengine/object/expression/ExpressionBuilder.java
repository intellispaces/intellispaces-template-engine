package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.function.compile.CompiledStatement;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.expression.Operand;

import java.util.List;
import java.util.Objects;

public final class ExpressionBuilder {
  private String statement;
  private String preparedStatement;
  private CompiledStatement compiledStatement;
  private List<Operand> operands;

  public static ExpressionBuilder get() {
    return new ExpressionBuilder();
  }

  public ExpressionBuilder statement(String statement) {
    this.statement = statement;
    return this;
  }

  public ExpressionBuilder preparedStatement(String preparedStatement) {
    this.preparedStatement = preparedStatement;
    return this;
  }

  public ExpressionBuilder compiledExpression(CompiledStatement compiledStatement) {
    this.compiledStatement = compiledStatement;
    return this;
  }

  public ExpressionBuilder operands(List<Operand> operands) {
    this.operands = operands;
    return this;
  }

  public Expression build() {
    validate();
    return new ExpressionObject(statement, preparedStatement, compiledStatement, List.copyOf(operands));
  }

  private void validate() {
    Objects.requireNonNull(statement);
    Objects.requireNonNull(preparedStatement);
    Objects.requireNonNull(compiledStatement);
    Objects.requireNonNull(operands);
  }

  private ExpressionBuilder() {}
}
