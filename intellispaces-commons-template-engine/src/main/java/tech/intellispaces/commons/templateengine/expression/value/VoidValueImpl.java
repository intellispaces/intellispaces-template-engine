package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;

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

  @Override
  public BooleanValue isEmpty() throws ResolveTemplateException {
    return BooleanValues.of(true);
  }

  @Override
  public BooleanValue isNotEmpty() throws ResolveTemplateException {
    return BooleanValues.of(false);
  }
}
