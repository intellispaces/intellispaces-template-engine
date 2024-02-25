package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.expression.Expression;

import java.util.Objects;

public final class MarkerPrintBuilder {
  private TextPosition position;
  private Expression outputExpression;

  public static MarkerPrintBuilder get() {
    return new MarkerPrintBuilder();
  }

  public MarkerPrintBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerPrintBuilder outputExpression(Expression outputExpression) {
    this.outputExpression = outputExpression;
    return this;
  }

  public MarkerPrint build() {
    validate();
    return new MarkerPrintObject(position, outputExpression);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(outputExpression);
  }

  private MarkerPrintBuilder() {}
}
