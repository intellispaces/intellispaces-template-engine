package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.template.source.position.PositionBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class TemplateElementContextBuilder {
  private Position position;
  private List<TemplateElement> templateElements;
  private Integer elementIndex;

  public static TemplateElementContextBuilder get() {
    return new TemplateElementContextBuilder();
  }

  public TemplateElementContextBuilder position(Position position) {
    this.position = PositionBuilder.build(position);
    return this;
  }

  public TemplateElementContextBuilder position(int offset, int row, int column) {
    this.position = PositionBuilder.build(offset, row, column);
    return this;
  }

  public TemplateElementContextBuilder templateElements(List<TemplateElement> templateElements) {
    this.templateElements = templateElements;
    return this;
  }

  public TemplateElementContextBuilder elementIndex(int elementIndex) {
    this.elementIndex = elementIndex;
    return this;
  }

  public TemplateElementContext build() {
    validate();
    return new TemplateElementContextImpl(position, Collections.unmodifiableList(templateElements), elementIndex);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(templateElements);
  }

  private TemplateElementContextBuilder() {}
}
