package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

import java.util.List;

class StatementWhenBranchImpl implements StatementWhenBranch {
  private final Expression condition;
  private final List<TemplateElement> subElements;

  StatementWhenBranchImpl(Expression condition, List<TemplateElement> subElements) {
    this.condition = condition;
    this.subElements = subElements;
  }

  @Override
  public Expression condition() {
    return condition;
  }

  @Override
  public List<TemplateElement> subElements() {
    return subElements;
  }
}
