package intellispaces.templateengine.text;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TextFunctions}.
 */
public class TextFunctionsTest {

  @Test
  public void testIsWordChar() {
    assertThat(TextFunctions.isWordChar('a')).isTrue();
    assertThat(TextFunctions.isWordChar('A')).isTrue();
    assertThat(TextFunctions.isWordChar('z')).isTrue();
    assertThat(TextFunctions.isWordChar('Z')).isTrue();
    assertThat(TextFunctions.isWordChar('a')).isTrue();

    assertThat(TextFunctions.isWordChar('0')).isTrue();
    assertThat(TextFunctions.isWordChar('1')).isTrue();
    assertThat(TextFunctions.isWordChar('2')).isTrue();
    assertThat(TextFunctions.isWordChar('3')).isTrue();
    assertThat(TextFunctions.isWordChar('4')).isTrue();
    assertThat(TextFunctions.isWordChar('5')).isTrue();
    assertThat(TextFunctions.isWordChar('6')).isTrue();
    assertThat(TextFunctions.isWordChar('7')).isTrue();
    assertThat(TextFunctions.isWordChar('8')).isTrue();
    assertThat(TextFunctions.isWordChar('9')).isTrue();

    assertThat(TextFunctions.isWordChar('_')).isTrue();

    assertThat(TextFunctions.isWordChar(' ')).isFalse();
    assertThat(TextFunctions.isWordChar('\t')).isFalse();
    assertThat(TextFunctions.isWordChar('\r')).isFalse();
    assertThat(TextFunctions.isWordChar('\n')).isFalse();

    assertThat(TextFunctions.isWordChar('+')).isFalse();
    assertThat(TextFunctions.isWordChar('-')).isFalse();
    assertThat(TextFunctions.isWordChar('*')).isFalse();
    assertThat(TextFunctions.isWordChar('?')).isFalse();
    assertThat(TextFunctions.isWordChar('#')).isFalse();
    assertThat(TextFunctions.isWordChar('%')).isFalse();
    assertThat(TextFunctions.isWordChar('&')).isFalse();
    assertThat(TextFunctions.isWordChar('(')).isFalse();
    assertThat(TextFunctions.isWordChar(')')).isFalse();
    assertThat(TextFunctions.isWordChar('[')).isFalse();
    assertThat(TextFunctions.isWordChar(']')).isFalse();
    assertThat(TextFunctions.isWordChar('{')).isFalse();
    assertThat(TextFunctions.isWordChar('}')).isFalse();
  }

  @Test
  public void testIsBeginWithLineBreakIgnoreBlanks() {
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\r\nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks(" \n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks(" \nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks(" \n\r")).isTrue();

    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\n")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\t\nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\nA")).isTrue();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n\r")).isTrue();

    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(TextFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testIsEndWithLineBreakIgnoreBlanks() {
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n ")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r\n ")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n\r ")).isTrue();

    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n\t")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r\n\t")).isTrue();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n\r\t")).isTrue();

    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\nA")).isFalse();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r\nA")).isFalse();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\n\rA")).isFalse();

    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(TextFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testRemoveLastGaps() {
    assertThat(TextFunctions.removeLastGaps("\n")).isEqualTo("\n");
    assertThat(TextFunctions.removeLastGaps("\r\n")).isEqualTo("\r\n");
    assertThat(TextFunctions.removeLastGaps("\n\r")).isEqualTo("\n\r");

    assertThat(TextFunctions.removeLastGaps("\n ")).isEqualTo("\n");
    assertThat(TextFunctions.removeLastGaps("\r\n ")).isEqualTo("\r\n");
    assertThat(TextFunctions.removeLastGaps("\n\r ")).isEqualTo("\n\r");

    assertThat(TextFunctions.removeLastGaps("\n\t")).isEqualTo("\n");
    assertThat(TextFunctions.removeLastGaps("\r\n\t")).isEqualTo("\r\n");
    assertThat(TextFunctions.removeLastGaps("\n\r\t")).isEqualTo("\n\r");

    assertThat(TextFunctions.removeLastGaps("a\n")).isEqualTo("a\n");
    assertThat(TextFunctions.removeLastGaps("b\r\n")).isEqualTo("b\r\n");
    assertThat(TextFunctions.removeLastGaps("c\n\r")).isEqualTo("c\n\r");

    assertThat(TextFunctions.removeLastGaps("\nA")).isEqualTo("\nA");
    assertThat(TextFunctions.removeLastGaps("\r\nA")).isEqualTo("\r\nA");

    assertThat(TextFunctions.removeLastGaps("  ")).isEqualTo("");

    assertThat(TextFunctions.removeLastGaps("")).isEqualTo("");
    assertThat(TextFunctions.removeLastGaps("\r")).isEqualTo("\r");
  }

  @Test
  public void testRemoveFirstBlanksAndLineBreak() {
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\r\n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\n\r")).isEqualTo("");

    assertThat(TextFunctions.removeFirstBlanksAndLineBreak(" \n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak(" \r\n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak(" \n\r")).isEqualTo("");

    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\t\n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\t\r\n")).isEqualTo("");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("\t\n\r")).isEqualTo("");

    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("A\n")).isEqualTo("A\n");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("A\r\n")).isEqualTo("A\r\n");
    assertThat(TextFunctions.removeFirstBlanksAndLineBreak("A\n\r")).isEqualTo("A\n\r");
  }
}
