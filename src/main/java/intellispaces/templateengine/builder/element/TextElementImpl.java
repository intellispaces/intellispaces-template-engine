package intellispaces.templateengine.builder.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.TextElement;

final class TextElementImpl implements TextElement {
  private final TextPosition position;
  private final String text;

  TextElementImpl(TextPosition position, String text) {
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
}
