package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.IntegerValue;

public final class IntegerValueBuilder {

  public static IntegerValue build(int value) {
    return new IntegerValueImpl(value);
  }
}
