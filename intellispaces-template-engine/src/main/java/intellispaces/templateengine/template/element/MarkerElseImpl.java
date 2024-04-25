package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;
import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

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
