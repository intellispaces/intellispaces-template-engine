package intellispaces.common.templateengine.expression.value;

import intellispaces.common.templateengine.exception.ResolveTemplateException;

import java.util.Objects;

class RealValueImpl extends AbstractValue implements RealValue {
  private final double value;

  RealValueImpl(double value) {
    this.value = value;
  }

  @Override
  public ValueType type() {
    return ValueTypes.Real;
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
      return BooleanValues.of(get() == ((RealValue) other).get());
    } else if (other.type() == ValueTypes.Integer) {
      return BooleanValues.of(get() == ((IntegerValue) other).get());
    }
    return BooleanValues.of(false);
  }

  @Override
  public RealValue invert() throws ResolveTemplateException {
    return RealValues.of(-get());
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
