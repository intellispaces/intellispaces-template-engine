package tech.intellispaces.framework.templateengine.template.source.block;

import tech.intellispaces.framework.templateengine.template.source.position.Position;

public final class SourceBlockBuilder {
  private Position position;
  private String value;
  private boolean marker;

  public static SourceBlockBuilder get() {
    return new SourceBlockBuilder();
  }

  public SourceBlockBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public SourceBlockBuilder value(String value) {
    this.value = value;
    return this;
  }

  public SourceBlockBuilder marker(boolean marker) {
    this.marker = marker;
    return this;
  }

  public SourceBlock build() {
    return new SourceBlockImpl(position, value, marker);
  }

  private SourceBlockBuilder() {}
}
