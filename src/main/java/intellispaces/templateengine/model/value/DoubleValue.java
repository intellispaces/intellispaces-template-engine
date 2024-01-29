package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;

import java.util.Objects;

public interface DoubleValue extends Value {

  double get();

  @Override
  default ValueType type() {
    return ValueTypes.Double;
  }

  @Override
  default BooleanValue eq(Value other) {
    if (other instanceof DoubleValue) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((DoubleValue) other).get()));
    } else if (other instanceof IntegerValue) {
      return BooleanValueBuilder.build(Objects.equals(get(), (double) ((IntegerValue) other).get()));
    } else {
      return BooleanValueBuilder.build(false);
    }
  }
}
