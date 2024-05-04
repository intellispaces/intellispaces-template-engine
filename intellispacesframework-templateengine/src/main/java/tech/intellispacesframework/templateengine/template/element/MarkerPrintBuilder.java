package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.source.position.Position;

import java.util.Objects;

public final class MarkerPrintBuilder {
  private Position position;
  private Expression outputExpression;

  public static MarkerPrintBuilder get() {
    return new MarkerPrintBuilder();
  }

  public MarkerPrintBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public MarkerPrintBuilder outputExpression(Expression outputExpression) {
    this.outputExpression = outputExpression;
    return this;
  }

  public MarkerPrint build() {
    validate();
    return new MarkerPrintImpl(position, outputExpression);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(outputExpression);
  }

  private MarkerPrintBuilder() {}
}
