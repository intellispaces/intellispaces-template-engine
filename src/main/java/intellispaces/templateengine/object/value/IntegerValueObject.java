package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.RealValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

final class IntegerValueObject extends AbstractValue implements IntegerValue {
  private final int value;

  IntegerValueObject(int value) {
    this.value = value;
  }

  @Override
  public int get() {
    return value;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public IntegerValue asInteger() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Integer) {
      return BooleanValueBuilder.build(get() == ((IntegerValue) other).get());
    } else if (other.type() == ValueTypes.Real) {
      return BooleanValueBuilder.build(get() == ((RealValue) other).get());
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public IntegerValue invert() throws ResolveTemplateException {
    return IntegerValueBuilder.build(-get());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!IntegerValue.class.isInstance(o)) {
      return false;
    }
    IntegerValue other = (IntegerValue) o;
    return get() == other.get();
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
