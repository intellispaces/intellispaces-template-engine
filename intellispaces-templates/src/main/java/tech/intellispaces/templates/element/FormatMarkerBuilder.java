package tech.intellispaces.templates.element;

import java.util.List;
import java.util.Objects;

public final class FormatMarkerBuilder {
  private TemplateElementContext context;
  private List<MarkerFormatType> types;

  FormatMarkerBuilder() {}

  public FormatMarkerBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public FormatMarkerBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public MarkerFormat get() {
    validate();
    return new FormatMarkerImpl(context, types);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(types);
  }
}
