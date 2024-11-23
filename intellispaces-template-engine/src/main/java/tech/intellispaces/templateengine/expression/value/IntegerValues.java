package tech.intellispaces.templateengine.expression.value;

public interface IntegerValues {

  static IntegerValue of(int value) {
    return new IntegerValueImpl(value);
  }
}
