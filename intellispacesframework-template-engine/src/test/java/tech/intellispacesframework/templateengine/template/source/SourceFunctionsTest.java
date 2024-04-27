package tech.intellispacesframework.templateengine.template.source;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SourceFunctions}.
 */
public class SourceFunctionsTest {

  @Test
  public void testIsWordChar() {
    assertThat(SourceFunctions.isWordChar('a')).isTrue();
    assertThat(SourceFunctions.isWordChar('A')).isTrue();
    assertThat(SourceFunctions.isWordChar('z')).isTrue();
    assertThat(SourceFunctions.isWordChar('Z')).isTrue();
    assertThat(SourceFunctions.isWordChar('a')).isTrue();

    assertThat(SourceFunctions.isWordChar('0')).isTrue();
    assertThat(SourceFunctions.isWordChar('1')).isTrue();
    assertThat(SourceFunctions.isWordChar('2')).isTrue();
    assertThat(SourceFunctions.isWordChar('3')).isTrue();
    assertThat(SourceFunctions.isWordChar('4')).isTrue();
    assertThat(SourceFunctions.isWordChar('5')).isTrue();
    assertThat(SourceFunctions.isWordChar('6')).isTrue();
    assertThat(SourceFunctions.isWordChar('7')).isTrue();
    assertThat(SourceFunctions.isWordChar('8')).isTrue();
    assertThat(SourceFunctions.isWordChar('9')).isTrue();

    assertThat(SourceFunctions.isWordChar('_')).isTrue();

    assertThat(SourceFunctions.isWordChar(' ')).isFalse();
    assertThat(SourceFunctions.isWordChar('\t')).isFalse();
    assertThat(SourceFunctions.isWordChar('\r')).isFalse();
    assertThat(SourceFunctions.isWordChar('\n')).isFalse();

    assertThat(SourceFunctions.isWordChar('+')).isFalse();
    assertThat(SourceFunctions.isWordChar('-')).isFalse();
    assertThat(SourceFunctions.isWordChar('*')).isFalse();
    assertThat(SourceFunctions.isWordChar('?')).isFalse();
    assertThat(SourceFunctions.isWordChar('#')).isFalse();
    assertThat(SourceFunctions.isWordChar('%')).isFalse();
    assertThat(SourceFunctions.isWordChar('&')).isFalse();
    assertThat(SourceFunctions.isWordChar('(')).isFalse();
    assertThat(SourceFunctions.isWordChar(')')).isFalse();
    assertThat(SourceFunctions.isWordChar('[')).isFalse();
    assertThat(SourceFunctions.isWordChar(']')).isFalse();
    assertThat(SourceFunctions.isWordChar('{')).isFalse();
    assertThat(SourceFunctions.isWordChar('}')).isFalse();
  }

  @Test
  public void testIsBeginWithLineBreakIgnoreBlanks() {
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(SourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testIsEndWithLineBreakIgnoreBlanks() {
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n ")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n ")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r ")).isTrue();

    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\t")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n\t")).isTrue();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r\t")).isTrue();

    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\nA")).isFalse();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\nA")).isFalse();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\rA")).isFalse();

    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(SourceFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testRemoveLastGaps() {
    assertThat(SourceFunctions.removeLastGaps("\n")).isEqualTo("\n");
    assertThat(SourceFunctions.removeLastGaps("\r\n")).isEqualTo("\r\n");
    assertThat(SourceFunctions.removeLastGaps("\n\r")).isEqualTo("\n\r");

    assertThat(SourceFunctions.removeLastGaps("\n ")).isEqualTo("\n");
    assertThat(SourceFunctions.removeLastGaps("\r\n ")).isEqualTo("\r\n");
    assertThat(SourceFunctions.removeLastGaps("\n\r ")).isEqualTo("\n\r");

    assertThat(SourceFunctions.removeLastGaps("\n\t")).isEqualTo("\n");
    assertThat(SourceFunctions.removeLastGaps("\r\n\t")).isEqualTo("\r\n");
    assertThat(SourceFunctions.removeLastGaps("\n\r\t")).isEqualTo("\n\r");

    assertThat(SourceFunctions.removeLastGaps("a\n")).isEqualTo("a\n");
    assertThat(SourceFunctions.removeLastGaps("b\r\n")).isEqualTo("b\r\n");
    assertThat(SourceFunctions.removeLastGaps("c\n\r")).isEqualTo("c\n\r");

    assertThat(SourceFunctions.removeLastGaps("\nA")).isEqualTo("\nA");
    assertThat(SourceFunctions.removeLastGaps("\r\nA")).isEqualTo("\r\nA");

    assertThat(SourceFunctions.removeLastGaps("  ")).isEqualTo("");

    assertThat(SourceFunctions.removeLastGaps("")).isEqualTo("");
    assertThat(SourceFunctions.removeLastGaps("\r")).isEqualTo("\r");
  }

  @Test
  public void testRemoveFirstBlanksAndLineBreak() {
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\r\n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\n\r")).isEqualTo("");

    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak(" \n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak(" \r\n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak(" \n\r")).isEqualTo("");

    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\t\n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\t\r\n")).isEqualTo("");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("\t\n\r")).isEqualTo("");

    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("A\n")).isEqualTo("A\n");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("A\r\n")).isEqualTo("A\r\n");
    assertThat(SourceFunctions.removeFirstBlanksAndLineBreak("A\n\r")).isEqualTo("A\n\r");
  }
}
