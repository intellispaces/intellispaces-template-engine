package intellispaces.templateengine.template.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.expression.Expression;
import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Map;

class StatementForeachImpl extends AbstractElement implements StatementForeach {
  private final Expression collectionExpression;
  private final String itemName;
  private final List<TemplateElement> subElements;

  StatementForeachImpl(Position position, Expression collectionExpression, String itemName, List<TemplateElement> subElements) {
    super(position);
    this.collectionExpression = collectionExpression;
    this.itemName = itemName;
    this.subElements = subElements;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.StatementForeach;
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

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ElementFunctions.resolve(this, variables);
  }
}
