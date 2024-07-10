package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.Objects;

public final class MarkerForeachBuilder {
  private TemplateElementContext context;
  private Expression collectionExpression;
  private String itemName;

  public static MarkerForeachBuilder get() {
    return new MarkerForeachBuilder();
  }

  public MarkerForeachBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerForeachBuilder collectionExpression(Expression collectionExpression) {
    this.collectionExpression = collectionExpression;
    return this;
  }

  public MarkerForeachBuilder itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public MarkerForeach build() {
    validate();
    return new MarkerForeachImpl(context, collectionExpression, itemName);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
  }

  private MarkerForeachBuilder() {}
}
