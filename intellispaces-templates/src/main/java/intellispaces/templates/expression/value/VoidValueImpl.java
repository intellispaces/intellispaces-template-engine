package intellispaces.templates.expression.value;

class VoidValueImpl extends AbstractValue implements VoidValue {

  VoidValueImpl() {}

  @Override
  public ValueType type() {
    return ValueTypes.Void;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Void) {
      return BooleanValues.of(true);
    }
    return BooleanValues.of(false);
  }
}
