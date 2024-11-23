package tech.intellispaces.templateengine.source.position;

class PositionImpl implements MutablePosition {
  private int offset;
  private int row;
  private int column;

  PositionImpl() {}

  PositionImpl(int offset, int row, int column) {
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
