package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.expression.value.Value;
import tech.intellispacesframework.templateengine.template.source.position.Position;

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
