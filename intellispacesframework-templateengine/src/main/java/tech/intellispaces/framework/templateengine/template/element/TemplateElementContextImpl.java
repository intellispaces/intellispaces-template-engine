package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;

import java.util.List;

class TemplateElementContextImpl implements TemplateElementContext {
  private final Position position;
  private final List<TemplateElement> templateElements;
  private final Integer elementIndex;

  TemplateElementContextImpl(Position position, List<TemplateElement> templateElements, Integer elementIndex) {
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
