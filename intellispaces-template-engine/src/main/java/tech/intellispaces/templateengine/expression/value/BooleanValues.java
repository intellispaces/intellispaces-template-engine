package tech.intellispaces.templateengine.expression.value;

public interface BooleanValues {

  static BooleanValue of(boolean value) {
    return new BooleanValueImpl(value);
  }
}
