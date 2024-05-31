package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;

import java.util.Objects;

public final class MarkerSetBuilder {
  private Position position;
  private String valueName;
  private Expression valueExpression;

  public static MarkerSetBuilder get() {
    return new MarkerSetBuilder();
  }

  public MarkerSetBuilder position(Position position) {
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
    return new MarkerSetImpl(position, valueName, valueExpression);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(valueName);
    Objects.requireNonNull(valueExpression);
  }

  private MarkerSetBuilder() {}
}
