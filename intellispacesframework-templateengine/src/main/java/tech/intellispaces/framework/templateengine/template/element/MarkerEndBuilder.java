package tech.intellispaces.framework.templateengine.template.element;

import java.util.Objects;

public final class MarkerEndBuilder {
  private TemplateElementContext context;

  public static MarkerEndBuilder get() {
    return new MarkerEndBuilder();
  }

  public MarkerEndBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerEnd build() {
    validate();
    return new MarkerEndImpl(context);
  }

  private void validate() {
    Objects.requireNonNull(context);
  }

  private MarkerEndBuilder() {}
}
