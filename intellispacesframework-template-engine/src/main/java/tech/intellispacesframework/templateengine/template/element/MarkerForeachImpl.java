package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.expression.value.Value;
import tech.intellispacesframework.templateengine.template.source.position.Position;

import java.util.Map;

class MarkerForeachImpl extends AbstractElement implements MarkerForeach {
  private final Expression collectionExpression;
  private final String itemName;

  MarkerForeachImpl(Position position, Expression collectionExpression, String itemName) {
    super(position);
    this.collectionExpression = collectionExpression;
    this.itemName = itemName;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerForeach;
  }

  @Override
  public Expression collectionExpression() {
    return collectionExpression;
  }

  @Override
  public String itemName() {
    return itemName;
  }

  @Override
  public String resolve(Map<String, Value> variables) {
    return ElementFunctions.resolve(this, variables);
  }
}
