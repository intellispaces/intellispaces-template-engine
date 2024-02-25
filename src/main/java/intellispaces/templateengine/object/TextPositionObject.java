package intellispaces.templateengine.object;

import intellispaces.templateengine.model.MutableTextPosition;

final class TextPositionObject implements MutableTextPosition {
  private int offset;
  private int row;
  private int column;

  TextPositionObject() {}

  TextPositionObject(int offset, int row, int column) {
    this.offset = offset;
    this.row = row;
    this.column = column;
  }

  @Override
  public int offset() {
    return offset;
  }

  @Override
  public int row() {
    return row;
  }

  @Override
  public int column() {
    return column;
  }

  @Override
  public void set(int offset, int row, int column) {
    this.offset = offset;
    this.row = row;
    this.column = column;
  }
}
