package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;
import intellispaces.common.templateengine.expression.value.Value;

import java.util.Map;

class ElseMarkerImpl extends AbstractElement implements MarkerElse {
  private final Expression condition;

  ElseMarkerImpl(TemplateElementContext context, Expression condition) {
    super(context);
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
      return "{{else when " + condition.statement() + "}}";
    }
  }
}
