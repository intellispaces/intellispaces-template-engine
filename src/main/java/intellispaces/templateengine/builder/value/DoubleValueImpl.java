package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.DoubleValue;

final class DoubleValueImpl implements DoubleValue {
  private final double value;

  DoubleValueImpl(double value) {
    this.value = value;
  }

  @Override
  public double get() {
    return value;
  }
}
