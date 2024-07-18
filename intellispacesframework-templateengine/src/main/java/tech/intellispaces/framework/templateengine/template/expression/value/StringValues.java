package tech.intellispaces.framework.templateengine.template.expression.value;

import java.util.Objects;

public interface StringValues {

  static StringValue get(char ch) {
    return new StringValueImpl("" + ch);
  }

  static StringValue get(String string) {
    Objects.requireNonNull(string);
    return new StringValueImpl(string);
  }
}
