package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Objects;

public final class StatementFormatBuilder {
  private Position position;
  private List<MarkerFormatType> types;
  private List<TemplateElement> subElements;

  public static StatementFormatBuilder get() {
    return new StatementFormatBuilder();
  }

  public StatementFormatBuilder position(Position position) {
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
    return new StatementFormatImpl(position, types, subElements);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(types);
    Objects.requireNonNull(subElements);
  }

  private StatementFormatBuilder() {}
}
