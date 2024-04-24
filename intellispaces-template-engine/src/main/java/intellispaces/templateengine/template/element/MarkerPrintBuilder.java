package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;
import intellispaces.templateengine.template.source.position.Position;

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
