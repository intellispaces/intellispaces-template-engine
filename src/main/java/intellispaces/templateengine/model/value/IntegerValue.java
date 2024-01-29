package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;

import java.util.Objects;

public interface IntegerValue extends Value {

  int get();

  @Override
  default ValueType type() {
    return ValueTypes.Integer;
  }

  @Override
  default BooleanValue eq(Value other) {
    if (other instanceof IntegerValue) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((IntegerValue) other).get()));
    } else if (other instanceof DoubleValue) {
        return BooleanValueBuilder.build(Objects.equals((double) get(), ((DoubleValue) other).get()));
    } else {
      return BooleanValueBuilder.build(false);
    }
  }
}
