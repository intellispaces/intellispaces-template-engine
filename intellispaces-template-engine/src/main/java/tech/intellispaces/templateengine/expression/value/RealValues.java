package tech.intellispaces.templateengine.expression.value;

public interface RealValues {

  static RealValue of(double value) {
    return new RealValueImpl(value);
  }
}
