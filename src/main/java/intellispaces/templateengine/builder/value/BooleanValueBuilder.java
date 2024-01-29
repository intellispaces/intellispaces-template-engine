package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.BooleanValue;

public final class BooleanValueBuilder {

  public static BooleanValue build(boolean value) {
    return new BooleanValueImpl(value);
  }
}
