package intellispaces.templateengine.position;

public interface MutablePosition extends Position {

  void set(int offset, int row, int column);
}
