package intellispaces.templateengine.template.expression.value;

public interface RealValueBuilder {

  static RealValue build(double value) {
    return new RealValueImpl(value);
  }
}
