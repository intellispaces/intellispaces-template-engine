package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

import java.util.Objects;

public final class WhenMarkerBuilder {
  private TemplateElementContext context;
  private Expression condition;

  WhenMarkerBuilder() {}

  public WhenMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public WhenMarkerBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerWhen get() {
    validate();
    return new WhenMarkerImpl(context, condition);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(condition);
  }
}
