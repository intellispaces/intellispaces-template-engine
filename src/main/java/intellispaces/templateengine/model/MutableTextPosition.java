package intellispaces.templateengine.model;

public interface MutableTextPosition extends TextPosition {

  void set(int offset, int row, int column);
}
