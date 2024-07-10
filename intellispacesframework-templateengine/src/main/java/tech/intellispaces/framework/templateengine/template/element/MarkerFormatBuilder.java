package tech.intellispaces.framework.templateengine.template.element;

import java.util.List;
import java.util.Objects;

public final class MarkerFormatBuilder {
  private TemplateElementContext context;
  private List<MarkerFormatType> types;

  public static MarkerFormatBuilder get() {
    return new MarkerFormatBuilder();
  }

  public MarkerFormatBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public MarkerFormatBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public MarkerFormat build() {
    validate();
    return new MarkerFormatImpl(context, types);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(types);
  }

  private MarkerFormatBuilder() {}
}
