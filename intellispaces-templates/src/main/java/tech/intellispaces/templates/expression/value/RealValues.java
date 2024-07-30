package tech.intellispaces.templates.expression.value;

public interface RealValues {

  static RealValue of(double value) {
    return new RealValueImpl(value);
  }
}
