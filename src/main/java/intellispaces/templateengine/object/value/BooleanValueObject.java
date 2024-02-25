package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

final class BooleanValueObject extends AbstractValue implements BooleanValue {
 private final boolean value;

  BooleanValueObject(boolean value) {
   this.value = value;
  }

  @Override
  public boolean get() {
    return value;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public BooleanValue asBoolean() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Boolean) {
      return BooleanValueBuilder.build(get() == ((BooleanValue) other).get());
    }
    return BooleanValueBuilder.build(false);
  }

  public BooleanValue invert() {
    return BooleanValueBuilder.build(!value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!BooleanValue.class.isInstance(o)) {
     return false;
    }
    BooleanValue other = (BooleanValue) o;
    return get() == other.get();
   }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
