package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.element.StatementWhenBranch;
import intellispaces.templateengine.model.element.TemplateElement;
import intellispaces.templateengine.model.expression.Expression;

import java.util.List;

final class StatementWhenBranchObject implements StatementWhenBranch {
  private final Expression condition;
  private final List<TemplateElement> subElements;

  StatementWhenBranchObject(Expression condition, List<TemplateElement> subElements) {
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
