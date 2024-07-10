package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.Objects;

public final class MarkerWhenBuilder {
  private TemplateElementContext context;
  private Expression condition;

  public static MarkerWhenBuilder get() {
    return new MarkerWhenBuilder();
  }

  public MarkerWhenBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerWhenBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerWhen build() {
    validate();
    return new MarkerWhenImpl(context, condition);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(condition);
  }

  private MarkerWhenBuilder() {}
}
