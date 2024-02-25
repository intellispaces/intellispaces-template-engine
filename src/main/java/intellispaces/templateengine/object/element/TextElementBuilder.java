package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.TextElement;

import java.util.Objects;

public final class TextElementBuilder {
  private TextPosition position;
  private String text;

  public static TextElementBuilder get() {
    return new TextElementBuilder();
  }

  public TextElementBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public TextElementBuilder text(String text) {
    this.text = text;
    return this;
  }

  public TextElement build() {
    validate();
    return new TextElementObject(position, text);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(text);
  }

  private TextElementBuilder() {}
}
