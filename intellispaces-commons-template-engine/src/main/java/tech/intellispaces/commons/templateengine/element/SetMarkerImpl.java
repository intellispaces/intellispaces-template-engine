package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.commons.templateengine.expression.Expression;
import tech.intellispaces.commons.templateengine.expression.value.Value;

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
