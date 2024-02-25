package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.expression.Expression;

import java.util.Objects;

public final class MarkerSetBuilder {
  private TextPosition position;
  private String valueName;
  private Expression valueExpression;

  public static MarkerSetBuilder get() {
    return new MarkerSetBuilder();
  }

  public MarkerSetBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerSetBuilder valueName(String valueName) {
    this.valueName = valueName;
    return this;
  }

  public MarkerSetBuilder valueExpression(Expression valueExpression) {
    this.valueExpression = valueExpression;
    return this;
  }

  public MarkerSet build() {
    validate();
    return new MarkerSetObject(position, valueName, valueExpression);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(valueName);
    Objects.requireNonNull(valueExpression);
  }

  private MarkerSetBuilder() {}
}
