package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.TextElement;

final class TextElementObject implements TextElement {
  private final TextPosition position;
  private final String text;

  TextElementObject(TextPosition position, String text) {
    this.position = position;
    this.text = text;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public String text() {
    return text;
  }

  @Override
  public String toString() {
    return text;
  }
}
