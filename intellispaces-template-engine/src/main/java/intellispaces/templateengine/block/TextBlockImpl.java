package intellispaces.templateengine.block;

import intellispaces.templateengine.position.Position;

class TextBlockImpl implements TextBlock {
  private final Position position;
  private final String value;
  private final boolean marker;

  TextBlockImpl(Position position, String value, boolean marker) {
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
    return text().length();
  }

  @Override
  public String text() {
    return isMarker() ? "{{" + value() + "}}" : value();
  }
}
