package intellispaces.templateengine.builder.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerEnd;

final class MarkerEndImpl implements MarkerEnd {
  private final TextPosition position;

  MarkerEndImpl(TextPosition position) {
    this.position = position;
  }

  @Override
  public TextPosition position() {
    return position;
  }
}
