package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;

public interface BooleanValue extends Value {

  boolean get();

  @Override
  default ValueType type() {
    return ValueTypes.Boolean;
  }

  default BooleanValue not() {
    return BooleanValueBuilder.build(!get());
  }
}
