package intellispaces.templateengine.object;

import intellispaces.templateengine.model.TextBlock;
import intellispaces.templateengine.model.TextPosition;

public final class TextBlockBuilder {
  private TextPosition position;
  private String value;
  private boolean marker;

  public static TextBlockBuilder get() {
    return new TextBlockBuilder();
  }

  public TextBlockBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public TextBlockBuilder value(String value) {
    this.value = value;
    return this;
  }

  public TextBlockBuilder marker(boolean marker) {
    this.marker = marker;
    return this;
  }

  public TextBlock build() {
    return new TextBlockObject(position,value, marker);
  }

  private TextBlockBuilder() {}
}
