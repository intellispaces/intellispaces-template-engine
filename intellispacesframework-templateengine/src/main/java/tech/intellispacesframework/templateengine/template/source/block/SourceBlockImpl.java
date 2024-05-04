package tech.intellispacesframework.templateengine.template.source.block;

import tech.intellispacesframework.templateengine.template.source.position.Position;

class SourceBlockImpl implements SourceBlock {
  private final Position position;
  private final String value;
  private final boolean marker;

  SourceBlockImpl(Position position, String value, boolean marker) {
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
