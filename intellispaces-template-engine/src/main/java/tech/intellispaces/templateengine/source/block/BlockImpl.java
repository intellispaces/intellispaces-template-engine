package tech.intellispaces.templateengine.source.block;

import tech.intellispaces.templateengine.source.position.Position;

class BlockImpl implements Block {
  private final Position position;
  private final String value;
  private final boolean marker;

  BlockImpl(Position position, String value, boolean marker) {
    this.position = position;
    this.value = value;
    this.marker = marker;
  }

  @Override
  public Position position() {
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

  @Override
  public int length() {
    return wording().length();
  }

  @Override
  public String wording() {
    return isMarker() ? "{{" + value() + "}}" : value();
  }
}
