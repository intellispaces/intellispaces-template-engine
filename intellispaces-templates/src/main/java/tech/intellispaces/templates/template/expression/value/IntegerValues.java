package tech.intellispaces.templates.template.expression.value;

public interface IntegerValues {

  static IntegerValue of(int value) {
    return new IntegerValueImpl(value);
  }
}
