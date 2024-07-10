package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.Objects;

public final class MarkerPrintBuilder {
  private TemplateElementContext context;
  private Expression outputExpression;

  public static MarkerPrintBuilder get() {
    return new MarkerPrintBuilder();
  }

  public MarkerPrintBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerPrintBuilder outputExpression(Expression outputExpression) {
    this.outputExpression = outputExpression;
    return this;
  }

  public MarkerPrint build() {
    validate();
    return new MarkerPrintImpl(context, outputExpression);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(outputExpression);
  }

  private MarkerPrintBuilder() {}
}
