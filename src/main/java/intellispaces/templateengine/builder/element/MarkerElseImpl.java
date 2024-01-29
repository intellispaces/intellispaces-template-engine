package intellispaces.templateengine.builder.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerElse;

final class MarkerElseImpl implements MarkerElse {
  private final TextPosition position;

  MarkerElseImpl(TextPosition position) {
    this.position = position;
  }

  @Override
  public TextPosition position() {
    return position;
  }
}
