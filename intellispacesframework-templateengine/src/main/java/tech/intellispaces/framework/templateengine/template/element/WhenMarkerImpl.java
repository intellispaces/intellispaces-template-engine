package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class WhenMarkerImpl extends AbstractElement implements MarkerWhen {
  private final Expression condition;

  WhenMarkerImpl(TemplateElementContext context, Expression condition) {
    super(context);
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
