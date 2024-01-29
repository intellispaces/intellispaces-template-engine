package intellispaces.templateengine.model.value;

public interface NullValue extends Value {

  @Override
  default ValueType type() {
    return ValueTypes.Null;
  }

  static NullValue get() {
    return NullValues.Instance;
  }

  enum NullValues implements NullValue {
    Instance
  }
}
