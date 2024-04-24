package intellispaces.templateengine.element;

import intellispaces.templateengine.position.Position;

import java.util.Objects;

public final class TextElementBuilder {
  private Position position;
  private String text;

  public static TextElementBuilder get() {
    return new TextElementBuilder();
  }

  public TextElementBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public TextElementBuilder text(String text) {
    this.text = text;
    return this;
  }

  public TextElement build() {
    validate();
    return new TextElementImpl(position, text);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(text);
  }

  private TextElementBuilder() {}
}
