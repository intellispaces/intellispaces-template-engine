package intellispaces.templateengine.expression.value;

public interface IntegerValueBuilder {

  static IntegerValue build(int value) {
    return new IntegerValueImpl(value);
  }
}
