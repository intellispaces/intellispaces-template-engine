package intellispaces.templateengine.builder;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.MutableTextPosition;

public final class TextPositionBuilder {

  public static TextPosition build(int offset, int row, int column) {
    return new ImplTextPosition(offset, row, column);
  }

  public static TextPosition build(TextPosition other) {
    return build(other.offset(), other.row(), other.column());
  }

  public static MutableTextPosition buildMutable() {
    return new ImplTextPosition();
  }

  public static MutableTextPosition buildMutable(int offset, int row, int column) {
    return new ImplTextPosition(offset, row, column);
  }

  private TextPositionBuilder() {}
}
