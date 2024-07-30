package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class WhenBranchStatementBuilder {
  private Expression condition;
  private List<TemplateElement> subElements;

  WhenBranchStatementBuilder() {}

  public WhenBranchStatementBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public WhenBranchStatementBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementWhenBranch get() {
    validate();
    return new WhenStatementBranchImpl(condition, subElements);
  }

  private void validate() {
    Objects.requireNonNull(subElements);
  }
}
