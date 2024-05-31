package tech.intellispaces.framework.templateengine.template.expression.value;

import java.util.Objects;

class BooleanValueImpl extends AbstractValue implements BooleanValue {
 private final boolean value;

  BooleanValueImpl(boolean value) {
   this.value = value;
  }

  @Override
  public ValueType type() {
    return ValueTypes.Boolean;
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
