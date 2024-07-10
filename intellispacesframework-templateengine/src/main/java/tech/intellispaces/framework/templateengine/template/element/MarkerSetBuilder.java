package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.Objects;

public final class MarkerSetBuilder {
  private TemplateElementContext context;
  private String valueName;
  private Expression valueExpression;

  public static MarkerSetBuilder get() {
    return new MarkerSetBuilder();
  }

  public MarkerSetBuilder context(TemplateElementContext context) {
    this.context = context;
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
    return new MarkerSetImpl(context, valueName, valueExpression);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(valueName);
    Objects.requireNonNull(valueExpression);
  }

  private MarkerSetBuilder() {}
}
