package tech.intellispaces.framework.templateengine.template.element;

import java.util.Objects;

public final class EndMarkerBuilder {
  private TemplateElementContext context;

  EndMarkerBuilder() {}

  public EndMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerEnd get() {
    validate();
    return new EndMarkerImpl(context);
  }

  private void validate() {
    Objects.requireNonNull(context);
  }
}
