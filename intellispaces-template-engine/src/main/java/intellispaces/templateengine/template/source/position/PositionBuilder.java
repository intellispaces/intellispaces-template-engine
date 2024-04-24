package intellispaces.templateengine.template.source.position;

public interface PositionBuilder {

  static Position build(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }

  static Position build(Position other) {
    return build(other.offset(), other.row(), other.column());
  }

  static MutablePosition buildMutable() {
    return new PositionImpl();
  }

  static MutablePosition buildMutable(int offset, int row, int column) {
    return new PositionImpl(offset, row, column);
  }
}
