package intellispaces.templateengine.expression.value;

public interface RealValueBuilder {

  static RealValue build(double value) {
    return new RealValueImpl(value);
  }
}
