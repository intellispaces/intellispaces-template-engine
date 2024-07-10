package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class StatementForeachBuilder {
  private TemplateElementContext context;
  private Expression collectionExpression;
  private String itemName;
  private List<TemplateElement> subElements;

  public static StatementForeachBuilder get() {
    return new StatementForeachBuilder();
  }

  public StatementForeachBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public StatementForeachBuilder collectionExpression(Expression collectionExpression) {
    this.collectionExpression = collectionExpression;
    return this;
  }

  public StatementForeachBuilder itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public StatementForeachBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementForeach build() {
    validate();
    return new StatementForeachImpl(context, collectionExpression, itemName, subElements);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
    Objects.requireNonNull(subElements);
  }

  private StatementForeachBuilder() {}
}
