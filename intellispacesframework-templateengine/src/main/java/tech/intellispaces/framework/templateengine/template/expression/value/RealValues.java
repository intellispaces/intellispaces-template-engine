package tech.intellispaces.framework.templateengine.template.expression.value;

public interface RealValues {

  static RealValue of(double value) {
    return new RealValueImpl(value);
  }
}
