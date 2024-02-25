package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerEnd;

final class MarkerEndObject implements MarkerEnd {
  private final TextPosition position;

  MarkerEndObject(TextPosition position) {
    this.position = position;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public String toString() {
    return "{{end}}";
  }
}
