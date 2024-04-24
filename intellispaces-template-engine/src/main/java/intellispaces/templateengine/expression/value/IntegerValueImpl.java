package intellispaces.templateengine.expression.value;

import intellispaces.templateengine.exception.ResolveTemplateException;

import java.util.Objects;

class IntegerValueImpl extends AbstractValue implements IntegerValue {
  private final int value;

  IntegerValueImpl(int value) {
    this.value = value;
  }

  @Override
  public ValueType type() {
    return ValueTypes.Integer;
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
