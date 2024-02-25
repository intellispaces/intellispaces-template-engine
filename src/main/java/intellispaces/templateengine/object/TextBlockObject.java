package intellispaces.templateengine.object;

import intellispaces.templateengine.model.TextBlock;
import intellispaces.templateengine.model.TextPosition;

class TextBlockObject implements TextBlock {
  private final TextPosition position;
  private final String value;
  private final boolean marker;

  TextBlockObject(TextPosition position, String value, boolean marker) {
    this.position = position;
    this.value = value;
    this.marker = marker;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public String value() {
    return value;
  }

  @Override
  public boolean isMarker() {
    return marker;
  }
}
