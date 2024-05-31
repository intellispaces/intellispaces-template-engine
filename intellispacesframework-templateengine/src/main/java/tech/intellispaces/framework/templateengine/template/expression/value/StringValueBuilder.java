package tech.intellispaces.framework.templateengine.template.expression.value;

import java.util.Objects;

public interface StringValueBuilder {

  static StringValue build(char ch) {
    return new StringValueImpl("" + ch);
  }

  static StringValue build(String string) {
    Objects.requireNonNull(string);
    return new StringValueImpl(string);
  }
}
