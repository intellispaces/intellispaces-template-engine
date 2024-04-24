package intellispaces.templateengine.block;

import intellispaces.templateengine.position.Position;

public final class TextBlockBuilder {
  private Position position;
  private String value;
  private boolean marker;

  public static TextBlockBuilder get() {
    return new TextBlockBuilder();
  }

  public TextBlockBuilder position(Position position) {
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
    return new TextBlockImpl(position,value, marker);
  }

  private TextBlockBuilder() {}
}
