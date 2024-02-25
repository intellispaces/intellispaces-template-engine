package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.BooleanValue;

public interface BooleanValueBuilder {

  static BooleanValue build(boolean value) {
    return new BooleanValueObject(value);
  }
}
