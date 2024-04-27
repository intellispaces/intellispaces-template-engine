package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.expression.value.Value;
import tech.intellispacesframework.templateengine.template.source.position.Position;

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
