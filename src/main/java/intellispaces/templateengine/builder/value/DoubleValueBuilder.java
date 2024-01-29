package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.DoubleValue;

public final class DoubleValueBuilder {

  public static DoubleValue build(double value) {
    return new DoubleValueImpl(value);
  }
}
