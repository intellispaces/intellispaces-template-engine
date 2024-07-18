package tech.intellispaces.framework.templateengine.template.source;

import tech.intellispaces.framework.commons.string.CharFunctions;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Template source text functions.
 */
public interface SourceFunctions {

  static boolean isWordChar(char ch) {
    return isLetter(ch) || isDigit(ch) || (ch == '_');
  }

  static boolean isBeginWithLinebreakIgnoreBlanks(String string) {
    char[] chars = string.toCharArray();
    int length = chars.length;
    if (length > 0) {
      int index = 0;
      while (index < length && CharFunctions.isGapChar(chars[index])) {
        index++;
      }
      if (
          (index < length && chars[index] == '\n') ||
              (index + 1 < length && chars[index] == '\r' && chars[index + 1] == '\n')
      ) {
        return true;
      }
    }
    return false;
  }

  static boolean isEndWithLinebreakIgnoreBlanks(String string) {
    char[] chars = string.toCharArray();
    int length = chars.length;
    if (length > 0) {
      int index = chars.length - 1;
      while (index >= 0 && CharFunctions.isGapChar(chars[index])) {
        index--;
      }
      if (
          (index >= 0 && chars[index] == '\n')
              || (index - 1 >= 0 && chars[index - 1] == '\r' && chars[index] == '\n')
              || (index - 1 >= 0 && chars[index - 1] == '\n' && chars[index] == '\r')
      ) {
        return true;
      }
    }
    return false;
  }

  static String removeLastGaps(String string) {
    char[] chars = string.toCharArray();
    int length = chars.length;
    if (length > 0) {
      int index = chars.length - 1;
      while (index >= 0 && CharFunctions.isGapChar(chars[index])) {
        index--;
      }
      return string.substring(0, index + 1);
    }
    return string;
  }

  static String removeFirstBlanksAndLinebreak(String string) {
    char[] chars = string.toCharArray();
    int length = chars.length;
    if (length > 0) {
      int index = 0;
      while (index < length && CharFunctions.isGapChar(chars[index])) {
        index++;
      }
      if (
          index + 1 < length &&
              ((chars[index] == '\r' && chars[index + 1] == '\n') || (chars[index] == '\n' && chars[index + 1] == '\r'))
      ) {
        return string.substring(index + 2);
      } else if (index < length && chars[index] == '\n') {
        return string.substring(index + 1);
      }
    }
    return string;
  }

  static String getTailBeforeLinebreak(String string) {
    char[] chars = string.toCharArray();
    int length = chars.length;
    if (length > 0) {
      int index = chars.length - 1;
      while (index >= 0 && (chars[index] != '\n' && chars[index] != '\r')) {
        index--;
      }
      if (index >= 0 && index != chars.length - 1) {
        return string.substring(index + 1);
      }
    }
    return "";
  }
}
