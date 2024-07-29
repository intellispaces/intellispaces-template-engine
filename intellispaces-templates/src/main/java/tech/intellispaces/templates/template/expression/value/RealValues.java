package tech.intellispaces.templates.template.expression.value;

public interface RealValues {

  static RealValue of(double value) {
    return new RealValueImpl(value);
  }
}
