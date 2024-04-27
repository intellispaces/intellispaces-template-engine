package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.source.position.Position;

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
