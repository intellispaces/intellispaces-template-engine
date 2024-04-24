package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class StatementWhenBranchBuilder {
  private Expression condition;
  private List<TemplateElement> subElements;

  public static StatementWhenBranchBuilder get() {
    return new StatementWhenBranchBuilder();
  }

  public StatementWhenBranchBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public StatementWhenBranchBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementWhenBranch build() {
    validate();
    return new StatementWhenBranchImpl(condition, subElements);
  }

  private void validate() {
    Objects.requireNonNull(subElements);
  }

  private StatementWhenBranchBuilder() {}
}
