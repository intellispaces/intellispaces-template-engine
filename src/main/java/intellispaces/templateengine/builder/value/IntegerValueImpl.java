package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.IntegerValue;

final class IntegerValueImpl implements IntegerValue {
  private final int value;

  IntegerValueImpl(int value) {
    this.value = value;
  }

  @Override
  public int get() {
    return value;
  }
}
