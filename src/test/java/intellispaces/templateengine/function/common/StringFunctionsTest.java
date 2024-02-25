package intellispaces.templateengine.function.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StringFunctions}.
 */
public class StringFunctionsTest {

  @Test
  public void testIsWordChar() {
    assertThat(StringFunctions.isWordChar('a')).isTrue();
    assertThat(StringFunctions.isWordChar('A')).isTrue();
    assertThat(StringFunctions.isWordChar('z')).isTrue();
    assertThat(StringFunctions.isWordChar('Z')).isTrue();
    assertThat(StringFunctions.isWordChar('a')).isTrue();

    assertThat(StringFunctions.isWordChar('0')).isTrue();
    assertThat(StringFunctions.isWordChar('1')).isTrue();
    assertThat(StringFunctions.isWordChar('2')).isTrue();
    assertThat(StringFunctions.isWordChar('3')).isTrue();
    assertThat(StringFunctions.isWordChar('4')).isTrue();
    assertThat(StringFunctions.isWordChar('5')).isTrue();
    assertThat(StringFunctions.isWordChar('6')).isTrue();
    assertThat(StringFunctions.isWordChar('7')).isTrue();
    assertThat(StringFunctions.isWordChar('8')).isTrue();
    assertThat(StringFunctions.isWordChar('9')).isTrue();

    assertThat(StringFunctions.isWordChar('_')).isTrue();

    assertThat(StringFunctions.isWordChar(' ')).isFalse();
    assertThat(StringFunctions.isWordChar('\t')).isFalse();
    assertThat(StringFunctions.isWordChar('\r')).isFalse();
    assertThat(StringFunctions.isWordChar('\n')).isFalse();

    assertThat(StringFunctions.isWordChar('+')).isFalse();
    assertThat(StringFunctions.isWordChar('-')).isFalse();
    assertThat(StringFunctions.isWordChar('*')).isFalse();
    assertThat(StringFunctions.isWordChar('?')).isFalse();
    assertThat(StringFunctions.isWordChar('#')).isFalse();
    assertThat(StringFunctions.isWordChar('%')).isFalse();
    assertThat(StringFunctions.isWordChar('&')).isFalse();
    assertThat(StringFunctions.isWordChar('(')).isFalse();
    assertThat(StringFunctions.isWordChar(')')).isFalse();
    assertThat(StringFunctions.isWordChar('[')).isFalse();
    assertThat(StringFunctions.isWordChar(']')).isFalse();
    assertThat(StringFunctions.isWordChar('{')).isFalse();
    assertThat(StringFunctions.isWordChar('}')).isFalse();
  }

  @Test
  public void testIsBeginWithLineBreakIgnoreBlanks() {
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\r\nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks(" \n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks(" \nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks(" \n\r")).isTrue();

    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\n")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\t\nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\nA")).isTrue();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n\r")).isTrue();

    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(StringFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testIsEndWithLineBreakIgnoreBlanks() {
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n ")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r\n ")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n\r ")).isTrue();

    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n\t")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r\n\t")).isTrue();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n\r\t")).isTrue();

    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\nA")).isFalse();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r\nA")).isFalse();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\n\rA")).isFalse();

    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(StringFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testRemoveLastBlanks() {
    assertThat(StringFunctions.removeLastBlanks("\n")).isEqualTo("\n");
    assertThat(StringFunctions.removeLastBlanks("\r\n")).isEqualTo("\r\n");
    assertThat(StringFunctions.removeLastBlanks("\n\r")).isEqualTo("\n\r");

    assertThat(StringFunctions.removeLastBlanks("\n ")).isEqualTo("\n");
    assertThat(StringFunctions.removeLastBlanks("\r\n ")).isEqualTo("\r\n");
    assertThat(StringFunctions.removeLastBlanks("\n\r ")).isEqualTo("\n\r");

    assertThat(StringFunctions.removeLastBlanks("\n\t")).isEqualTo("\n");
    assertThat(StringFunctions.removeLastBlanks("\r\n\t")).isEqualTo("\r\n");
    assertThat(StringFunctions.removeLastBlanks("\n\r\t")).isEqualTo("\n\r");

    assertThat(StringFunctions.removeLastBlanks("a\n")).isEqualTo("a\n");
    assertThat(StringFunctions.removeLastBlanks("b\r\n")).isEqualTo("b\r\n");
    assertThat(StringFunctions.removeLastBlanks("c\n\r")).isEqualTo("c\n\r");

    assertThat(StringFunctions.removeLastBlanks("\nA")).isEqualTo("\nA");
    assertThat(StringFunctions.removeLastBlanks("\r\nA")).isEqualTo("\r\nA");

    assertThat(StringFunctions.removeLastBlanks("  ")).isEqualTo("");

    assertThat(StringFunctions.removeLastBlanks("")).isEqualTo("");
    assertThat(StringFunctions.removeLastBlanks("\r")).isEqualTo("\r");
  }

  @Test
  public void testRemoveFirstBlanksAndLineBreak() {
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\r\n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\n\r")).isEqualTo("");

    assertThat(StringFunctions.removeFirstBlanksAndLineBreak(" \n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak(" \r\n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak(" \n\r")).isEqualTo("");

    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\t\n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\t\r\n")).isEqualTo("");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("\t\n\r")).isEqualTo("");

    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("A\n")).isEqualTo("A\n");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("A\r\n")).isEqualTo("A\r\n");
    assertThat(StringFunctions.removeFirstBlanksAndLineBreak("A\n\r")).isEqualTo("A\n\r");
  }
}
