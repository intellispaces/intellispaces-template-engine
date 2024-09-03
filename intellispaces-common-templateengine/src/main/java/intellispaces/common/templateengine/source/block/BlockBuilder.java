package intellispaces.common.templateengine.source.block;

import intellispaces.common.templateengine.source.position.Position;

public final class BlockBuilder {
  private Position position;
  private String value;
  private boolean marker;

  BlockBuilder() {}

  public BlockBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public BlockBuilder value(String value) {
    this.value = value;
    return this;
  }

  public BlockBuilder marker(boolean marker) {
    this.marker = marker;
    return this;
  }

  public Block get() {
    return new BlockImpl(position, value, marker);
  }
}
