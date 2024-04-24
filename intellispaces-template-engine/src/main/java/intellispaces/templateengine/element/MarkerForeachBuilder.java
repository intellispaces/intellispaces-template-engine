package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;
import intellispaces.templateengine.position.Position;

import java.util.Objects;

public final class MarkerForeachBuilder {
  private Position position;
  private Expression collectionExpression;
  private String itemName;

  public static MarkerForeachBuilder get() {
    return new MarkerForeachBuilder();
  }

  public MarkerForeachBuilder position(Position position) {
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
    return new MarkerForeachImpl(position, collectionExpression, itemName);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
  }

  private MarkerForeachBuilder() {}
}
