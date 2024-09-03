package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.exception.ResolveTemplateException;
import intellispaces.common.templateengine.expression.Expression;
import intellispaces.common.templateengine.expression.value.Value;

import java.util.Map;

class SetMarkerImpl extends AbstractElement implements MarkerSet {
  private final String valueName;
  private final Expression valueExpression;

  SetMarkerImpl(TemplateElementContext context, String valueName, Expression valueExpression) {
    super(context);
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
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{set " + valueName + " = " + valueExpression.statement() + "}}";
  }
}
