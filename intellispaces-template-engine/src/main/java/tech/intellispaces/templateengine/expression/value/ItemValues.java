package tech.intellispaces.templateengine.expression.value;

public interface ItemValues {

  static ItemValueBuilder build() {
    return new ItemValueBuilder();
  }
}
