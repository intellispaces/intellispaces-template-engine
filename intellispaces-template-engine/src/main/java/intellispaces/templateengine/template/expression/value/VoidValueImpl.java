package intellispaces.templateengine.template.expression.value;

class VoidValueImpl extends AbstractValue implements VoidValue {

  @Override
  public ValueType type() {
    return ValueTypes.Void;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Void) {
      return BooleanValueBuilder.build(true);
    }
    return BooleanValueBuilder.build(false);
  }
}
