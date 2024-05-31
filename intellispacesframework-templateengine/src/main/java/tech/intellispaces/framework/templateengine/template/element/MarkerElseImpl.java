package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class MarkerElseImpl extends AbstractElement implements MarkerElse {
  private final Expression condition;

  MarkerElseImpl(Position position, Expression condition) {
    super(position);
    this.condition = condition;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerElse;
  }

  @Override
  public Expression condition() {
    return condition;
  }

  @Override
  public String resolve(Map<String, Value> variables) {
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    if (condition == null) {
      return "{{else}}";
    } else {
      return "{{else " + condition.statement() + "}}";
    }
  }
}
