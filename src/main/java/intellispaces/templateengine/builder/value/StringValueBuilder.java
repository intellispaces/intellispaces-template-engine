package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.StringValue;

public final class StringValueBuilder {

  public static StringValue build(String string) {
    return new StringValueImpl(string);
  }
}
