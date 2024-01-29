package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.StringValue;

final class StringValueImpl implements StringValue {
  private final String value;

  StringValueImpl(String value) {
    this.value = value;
  }

  @Override
  public String get() {
    return value;
  }
}
