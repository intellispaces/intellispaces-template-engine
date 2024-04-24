package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.source.position.Position;

abstract class AbstractElement implements TemplateElement {
  private final Position position;

  AbstractElement(Position position) {
    this.position = position;
  }

  @Override
  public Position position() {
    return position;
  }
}
