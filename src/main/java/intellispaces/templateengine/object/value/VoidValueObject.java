package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.Value;
import intellispaces.templateengine.model.value.VoidValue;

public class VoidValueObject extends AbstractValue implements VoidValue {

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Void) {
      return BooleanValueBuilder.build(true);
    }
    return BooleanValueBuilder.build(false);
  }
}
