package tech.intellispaces.framework.templateengine.template.expression.value;

public interface IntegerValues {

  static IntegerValue of(int value) {
    return new IntegerValueImpl(value);
  }
}
