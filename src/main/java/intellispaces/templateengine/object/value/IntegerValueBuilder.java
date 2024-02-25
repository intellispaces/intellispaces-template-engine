package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.IntegerValue;

public interface IntegerValueBuilder {

  static IntegerValue build(int value) {
    return new IntegerValueObject(value);
  }
}
