package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
  }
}
