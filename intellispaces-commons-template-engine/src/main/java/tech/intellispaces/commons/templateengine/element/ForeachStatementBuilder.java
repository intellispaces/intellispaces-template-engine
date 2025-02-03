package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class ForeachStatementBuilder {
  private TemplateElementContext context;
  private Expression collectionExpression;
  private String itemName;
  private List<TemplateElement> subElements;

  ForeachStatementBuilder() {}

  public ForeachStatementBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public ForeachStatementBuilder collectionExpression(Expression collectionExpression) {
    this.collectionExpression = collectionExpression;
    return this;
  }

  public ForeachStatementBuilder itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public ForeachStatementBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementForeach get() {
    validate();
    return new ForeachStatementImpl(context, collectionExpression, itemName, subElements);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
    Objects.requireNonNull(subElements);
  }
}
