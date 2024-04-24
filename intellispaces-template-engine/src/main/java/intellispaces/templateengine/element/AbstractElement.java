package intellispaces.templateengine.element;

import intellispaces.templateengine.position.Position;

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
