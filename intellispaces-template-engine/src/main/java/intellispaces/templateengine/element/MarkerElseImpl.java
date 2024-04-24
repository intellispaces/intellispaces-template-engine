package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
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
