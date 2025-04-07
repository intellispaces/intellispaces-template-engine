package tech.intellispaces.templateengine.source.position;

public interface MutablePosition extends Position {

  void set(int offset, int row, int column);
}
