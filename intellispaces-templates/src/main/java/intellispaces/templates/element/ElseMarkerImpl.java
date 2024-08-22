package intellispaces.templates.element;

import intellispaces.templates.expression.Expression;
import intellispaces.templates.expression.value.Value;

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
      return "{{else " + condition.statement() + "}}";
    }
  }
}
