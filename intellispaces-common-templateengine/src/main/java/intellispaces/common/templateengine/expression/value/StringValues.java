package intellispaces.common.templateengine.expression.value;

import java.util.Objects;

public interface StringValues {

  static StringValue of(char ch) {
    return new StringValueImpl("" + ch);
  }

  static StringValue of(String string) {
    Objects.requireNonNull(string);
    return new StringValueImpl(string);
  }
}
