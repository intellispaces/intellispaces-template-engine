package intellispaces.templateengine.model.value;

import intellispaces.commons.StringFunctions;
import intellispaces.templateengine.builder.value.BooleanValueBuilder;
import intellispaces.templateengine.builder.value.StringValueBuilder;

import java.util.Objects;

public interface StringValue extends Value {

  String get();

  @Override
  default ValueType type() {
    return ValueTypes.String;
  }

  @Override
  default BooleanValue isBlank() {
    return BooleanValueBuilder.build(get() == null || get().isBlank());
  }

  @Override
  default StringValue capitalizeFirstLetter() {
    return StringValueBuilder.build(StringFunctions.capitalizeFirstLetter(get()));
  }

  @Override
  default BooleanValue eq(Value other) {
    if (other instanceof StringValue) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((StringValue) other).get()));
    } else {
      return BooleanValueBuilder.build(false);
    }
  }
}
