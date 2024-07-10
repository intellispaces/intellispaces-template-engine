package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.List;
import java.util.Map;

class StatementForeachImpl extends AbstractElement implements StatementForeach {
  private final Expression collectionExpression;
  private final String itemName;
  private final List<TemplateElement> subElements;

  StatementForeachImpl(TemplateElementContext context, Expression collectionExpression, String itemName, List<TemplateElement> subElements) {
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
