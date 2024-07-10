package tech.intellispaces.framework.templateengine.template.element;

import java.util.List;
import java.util.Objects;

public final class StatementFormatBuilder {
  private TemplateElementContext context;
  private List<MarkerFormatType> types;
  private List<TemplateElement> subElements;

  public static StatementFormatBuilder get() {
    return new StatementFormatBuilder();
  }

  public StatementFormatBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public StatementFormatBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public StatementFormatBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementFormat build() {
    validate();
    return new StatementFormatImpl(context, types, subElements);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(types);
    Objects.requireNonNull(subElements);
  }

  private StatementFormatBuilder() {}
}
