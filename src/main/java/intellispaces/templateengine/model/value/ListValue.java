package intellispaces.templateengine.model.value;

import intellispaces.templateengine.object.value.ValueTypes;

import java.util.List;

/**
 * List value.
 */
public interface ListValue extends Value {

  List<Value> get();

  @Override
  default ValueType type() {
    return ValueTypes.List;
  }
}
