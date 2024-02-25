package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerFormatType;
import intellispaces.templateengine.model.element.StatementFormat;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;
import java.util.Objects;

public final class StatementFormatBuilder {
  private TextPosition position;
  private List<MarkerFormatType> types;
  private List<TemplateElement> subElements;

  public static StatementFormatBuilder get() {
    return new StatementFormatBuilder();
  }

  public StatementFormatBuilder position(TextPosition position) {
    this.position = position;
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
    return new StatementFormatObject(position, types, subElements);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(types);
    Objects.requireNonNull(subElements);
  }

  private StatementFormatBuilder() {}
}
