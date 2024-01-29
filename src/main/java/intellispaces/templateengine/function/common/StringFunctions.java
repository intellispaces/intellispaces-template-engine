package intellispaces.templateengine.function.common;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public interface StringFunctions {

  static boolean isNumberChar(int ch) {
    return isDigit((char) ch) || ch == '.';
  }

  static boolean isIdentifierChar(char ch) {
    return isLetter(ch) || isDigit(ch) || (ch == '_');
  }

  static boolean isSeparatorChar(char ch) {
    return isPresent(ch, ' ', '\t', '(', ')', ',', '.');
  }

  static boolean isPresent(char ch, char... expectedChars) {
    for (final var curChar : expectedChars) {
      if (curChar == ch) {
        return true;
      }
    }
    return false;
  }
}
