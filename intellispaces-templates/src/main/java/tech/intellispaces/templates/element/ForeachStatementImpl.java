package tech.intellispaces.templates.element;

import tech.intellispaces.templates.exception.ResolveTemplateException;
import tech.intellispaces.templates.expression.Expression;
import tech.intellispaces.templates.expression.value.Value;

import java.util.List;
import java.util.Map;

class ForeachStatementImpl extends AbstractElement implements StatementForeach {
  private final Expression collectionExpression;
  private final String itemName;
  private final List<TemplateElement> subElements;

  ForeachStatementImpl(
      TemplateElementContext context,
      Expression collectionExpression,
      String itemName,
      List<TemplateElement> subElements
  ) {
    super(context);
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
