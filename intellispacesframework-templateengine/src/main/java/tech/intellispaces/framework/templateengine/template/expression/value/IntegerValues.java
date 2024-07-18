package tech.intellispaces.framework.templateengine.template.expression.value;

public interface IntegerValues {

  static IntegerValue get(int value) {
    return new IntegerValueImpl(value);
  }
}
