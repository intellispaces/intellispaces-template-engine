package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.expression.Expression;

final class MarkerForeachObject implements MarkerForeach {
  private final TextPosition position;
  private final Expression collectionExpression;
  private final String itemName;

  MarkerForeachObject(TextPosition position, Expression collectionExpression, String itemName) {
    this.position = position;
    this.collectionExpression = collectionExpression;
    this.itemName = itemName;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public Expression collectionExpression() {
    return collectionExpression;
  }

  @Override
  public String itemName() {
    return itemName;
  }
}
