package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class ForeachMarkerImpl extends AbstractElement implements MarkerForeach {
  private final Expression collectionExpression;
  private final String itemName;

  ForeachMarkerImpl(TemplateElementContext context, Expression collectionExpression, String itemName) {
    super(context);
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
