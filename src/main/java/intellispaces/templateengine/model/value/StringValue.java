package intellispaces.templateengine.model.value;

import intellispaces.templateengine.object.value.ValueTypes;

/**
 * String value.
 */
public interface StringValue extends Value {

  String get();

  @Override
  default ValueType type() {
    return ValueTypes.String;
  }


}
