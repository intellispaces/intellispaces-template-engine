package tech.intellispaces.framework.templateengine.template.expression.value;

class VoidValueImpl extends AbstractValue implements VoidValue {

  VoidValueImpl() {}

  @Override
  public ValueType type() {
    return ValueTypes.Void;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Void) {
      return BooleanValues.get(true);
    }
    return BooleanValues.get(false);
  }
}
