package tech.intellispaces.templateengine.source.position;

public interface Positions {

  static Position of(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }

  static Position of(Position other) {
    return of(other.offset(), other.row(), other.column());
  }

  static MutablePosition mutable() {
    return new PositionImpl();
  }

  static MutablePosition mutable(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }
}
