package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.StatementForeach;
import intellispaces.templateengine.model.element.TemplateElement;
import intellispaces.templateengine.model.expression.Expression;

import java.util.List;

final class StatementForeachObject implements StatementForeach {
  private final TextPosition position;
  private final Expression collectionExpression;
  private final String itemName;
  private final List<TemplateElement> subElements;

  StatementForeachObject(TextPosition position, Expression collectionExpression, String itemName, List<TemplateElement> subElements) {
    this.position = position;
    this.collectionExpression = collectionExpression;
    this.itemName = itemName;
    this.subElements = subElements;
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

  @Override
  public List<TemplateElement> subElements() {
    return subElements;
  }
}
