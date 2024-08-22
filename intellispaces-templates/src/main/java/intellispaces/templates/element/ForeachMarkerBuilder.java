package intellispaces.templates.element;

import intellispaces.templates.expression.Expression;

import java.util.Objects;

public final class ForeachMarkerBuilder {
  private TemplateElementContext context;
  private Expression collectionExpression;
  private String itemName;

  ForeachMarkerBuilder() {}

  public ForeachMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public ForeachMarkerBuilder collectionExpression(Expression collectionExpression) {
    this.collectionExpression = collectionExpression;
    return this;
  }

  public ForeachMarkerBuilder itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public MarkerForeach get() {
    validate();
    return new ForeachMarkerImpl(context, collectionExpression, itemName);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
  }
}
