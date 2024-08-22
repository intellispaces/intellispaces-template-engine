package intellispaces.templates.element;

import intellispaces.templates.expression.Expression;

import java.util.Objects;

public final class ElseMarkerBuilder {
  private TemplateElementContext context;
  private Expression condition;

  ElseMarkerBuilder() {}

  public ElseMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public ElseMarkerBuilder condition(Expression condition) {
    this.condition = condition;
    return this;
  }

  public MarkerElse get() {
    validate();
    return new ElseMarkerImpl(context, condition);
  }

  private void validate() {
    Objects.requireNonNull(context);
  }
}
