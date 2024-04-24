package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{when " + condition.statement() + "}}";
  }
}
