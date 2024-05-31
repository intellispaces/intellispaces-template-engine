package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class MarkerWhenImpl extends AbstractElement implements MarkerWhen {
  private final Expression condition;

  MarkerWhenImpl(Position position, Expression condition) {
    super(position);
    this.condition = condition;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerWhen;
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
    return "{{when " + condition.statement() + "}}";
  }
}
