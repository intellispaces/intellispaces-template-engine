package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.Objects;

public final class MarkerElseBuilder {
  private TemplateElementContext context;
  private Expression condition;

  public static MarkerElseBuilder get() {
    return new MarkerElseBuilder();
  }

  public MarkerElseBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerElseBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerElse build() {
    validate();
    return new MarkerElseImpl(context, condition);
  }

  private void validate() {
    Objects.requireNonNull(context);
  }

  private MarkerElseBuilder() {}
}
