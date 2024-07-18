package tech.intellispaces.framework.templateengine.template.source.position;

public interface Positions {

  static Position get(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }

  static Position copy(Position other) {
    return get(other.offset(), other.row(), other.column());
  }

  static MutablePosition getMutable() {
    return new PositionImpl();
  }

  static MutablePosition getMutable(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }
}
