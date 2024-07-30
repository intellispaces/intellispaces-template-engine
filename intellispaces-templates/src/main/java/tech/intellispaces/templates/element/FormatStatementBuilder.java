package tech.intellispaces.templates.element;

import java.util.List;
import java.util.Objects;

public final class FormatStatementBuilder {
  private TemplateElementContext context;
  private List<MarkerFormatType> types;
  private List<TemplateElement> subElements;

  FormatStatementBuilder() {}

  public FormatStatementBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public FormatStatementBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public FormatStatementBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementFormat get() {
    validate();
    return new FormatStatementImpl(context, types, subElements);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(types);
    Objects.requireNonNull(subElements);
  }
}
