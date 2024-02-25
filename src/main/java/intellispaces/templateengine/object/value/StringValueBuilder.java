package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.StringValue;

import java.util.Objects;

public interface StringValueBuilder {

  static StringValue build(char ch) {
    return new StringValueObject("" + ch);
  }

  static StringValue build(String string) {
    Objects.requireNonNull(string);
    return new StringValueObject(string);
  }
}
