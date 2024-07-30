package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

import java.util.Objects;

public final class SetMarkerBuilder {
  private TemplateElementContext context;
  private String valueName;
  private Expression valueExpression;

  SetMarkerBuilder() {}

  public SetMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public SetMarkerBuilder valueName(String valueName) {
    this.valueName = valueName;
    return this;
  }

  public SetMarkerBuilder valueExpression(Expression valueExpression) {
    this.valueExpression = valueExpression;
    return this;
  }

  public MarkerSet get() {
    validate();
    return new SetMarkerImpl(context, valueName, valueExpression);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(valueName);
    Objects.requireNonNull(valueExpression);
  }
}
