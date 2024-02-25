package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.expression.Expression;

import java.util.Objects;

public final class MarkerForeachBuilder {
  private TextPosition position;
  private Expression collectionExpression;
  private String itemName;

  public static MarkerForeachBuilder get() {
    return new MarkerForeachBuilder();
  }

  public MarkerForeachBuilder position(TextPosition position) {
    this.position = position;
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
    return new MarkerForeachObject(position, collectionExpression, itemName);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
  }

  private MarkerForeachBuilder() {}
}
