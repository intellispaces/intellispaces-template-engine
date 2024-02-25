package intellispaces.templateengine.model.value;

import intellispaces.templateengine.object.value.ValueTypes;

import java.util.Map;

/**
 * Map value.
 */
public interface MapValue extends Value {

  Map<Value, Value> get();

  @Override
  default ValueType type() {
    return ValueTypes.Map;
  }
}
