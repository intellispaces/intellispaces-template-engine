package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

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
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{set " + valueName + " = " + valueExpression.statement() + "}}";
  }
}
