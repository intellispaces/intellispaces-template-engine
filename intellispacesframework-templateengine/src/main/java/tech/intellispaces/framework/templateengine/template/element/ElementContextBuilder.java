package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.template.source.position.Positions;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ElementContextBuilder {
  private Position position;
  private List<TemplateElement> templateElements;
  private Integer elementIndex;

  ElementContextBuilder() {}

  public ElementContextBuilder position(Position position) {
    this.position = Positions.of(position);
    return this;
  }

  public ElementContextBuilder position(int offset, int row, int column) {
    this.position = Positions.of(offset, row, column);
    return this;
  }

  public ElementContextBuilder templateElements(List<TemplateElement> templateElements) {
    this.templateElements = templateElements;
    return this;
  }

  public ElementContextBuilder elementIndex(int elementIndex) {
    this.elementIndex = elementIndex;
    return this;
  }

  public TemplateElementContext get() {
    validate();
    return new ElementContextImpl(position, Collections.unmodifiableList(templateElements), elementIndex);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(templateElements);
  }
}
