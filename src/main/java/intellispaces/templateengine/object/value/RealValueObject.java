package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.RealValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

final class RealValueObject extends AbstractValue implements RealValue {
  private final double value;

  RealValueObject(double value) {
    this.value = value;
  }

  public double get() {
    return value;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public RealValue asReal() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Real) {
      return BooleanValueBuilder.build(get() == ((RealValue) other).get());
    } else if (other.type() == ValueTypes.Integer) {
      return BooleanValueBuilder.build(get() == ((IntegerValue) other).get());
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public RealValue invert() throws ResolveTemplateException {
    return RealValueBuilder.build(-get());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!RealValue.class.isInstance(o)) {
      return false;
    }
    RealValue other = (RealValue) o;
    return get() == other.get();
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
