package intellispaces.templateengine.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

import java.util.Map;

class MarkerSetImpl extends AbstractElement implements MarkerSet {
  private final String valueName;
  private final Expression valueExpression;

  MarkerSetImpl(Position position, String valueName, Expression valueExpression) {
    super(position);
    this.valueName = valueName;
    this.valueExpression = valueExpression;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerSet;
  }

  @Override
  public String valueName() {
    return valueName;
  }

  @Override
  public Expression valueExpression() {
    return valueExpression;
  }

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{set " + valueName + " = " + valueExpression.statement() + "}}";
  }
}
