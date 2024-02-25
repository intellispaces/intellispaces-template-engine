package intellispaces.templateengine.model.value;

import intellispaces.templateengine.object.value.ValueTypes;

/**
 * Void value.
 */
public interface VoidValue extends Value {

  @Override
  default ValueType type() {
    return ValueTypes.Void;
  }

  @Override
  default Value origin() {
    return this;
  }
}
