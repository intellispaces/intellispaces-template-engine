package tech.intellispaces.framework.templateengine.template.expression.value;

public interface RealValues {

  static RealValue get(double value) {
    return new RealValueImpl(value);
  }
}
