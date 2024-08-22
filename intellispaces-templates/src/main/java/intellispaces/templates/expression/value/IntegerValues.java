package intellispaces.templates.expression.value;

public interface IntegerValues {

  static IntegerValue of(int value) {
    return new IntegerValueImpl(value);
  }
}
