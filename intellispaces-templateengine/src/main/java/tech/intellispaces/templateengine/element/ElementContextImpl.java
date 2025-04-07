package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.source.position.Position;

import java.util.List;

class ElementContextImpl implements TemplateElementContext {
  private final Position position;
  private final List<TemplateElement> templateElements;
  private final Integer elementIndex;

  ElementContextImpl(Position position, List<TemplateElement> templateElements, Integer elementIndex) {
    this.position = position;
    this.templateElements = templateElements;
    this.elementIndex = elementIndex;
  }

  @Override
  public Position position() {
    return position;
  }

  @Override
  public List<TemplateElement> templateElements() {
    return templateElements;
  }

  @Override
  public Integer elementIndex() {
    return elementIndex;
  }
}
