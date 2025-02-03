package tech.intellispaces.commons.templateengine.source;

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
  public void testIsBeginWithLinebreakIgnoreBlanks() {
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks(" \n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks(" \r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks(" \nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks(" \r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks(" \n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\t\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\t\r\n")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\t\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\t\r\nA")).isTrue();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\t\n\r")).isTrue();

    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("")).isFalse();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\r")).isFalse();
    assertThat(SourceFunctions.isBeginWithLinebreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testIsEndWithLinebreakIgnoreBlanks() {
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n ")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r\n ")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n\r ")).isTrue();

    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n\t")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r\n\t")).isTrue();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n\r\t")).isTrue();

    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\nA")).isFalse();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r\nA")).isFalse();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\n\rA")).isFalse();

    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("")).isFalse();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r")).isFalse();
    assertThat(SourceFunctions.isEndWithLinebreakIgnoreBlanks("\r")).isFalse();
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

    assertThat(SourceFunctions.removeLastGaps("  ")).isEmpty();

    assertThat(SourceFunctions.removeLastGaps("")).isEmpty();
    assertThat(SourceFunctions.removeLastGaps("\r")).isEqualTo("\r");
  }

  @Test
  public void testRemoveFirstBlanksAndLinebreak() {
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\r\n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\n\r")).isEmpty();

    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak(" \n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak(" \r\n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak(" \n\r")).isEmpty();

    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\t\n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\t\r\n")).isEmpty();
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("\t\n\r")).isEmpty();

    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("A\n")).isEqualTo("A\n");
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("A\r\n")).isEqualTo("A\r\n");
    assertThat(SourceFunctions.removeFirstBlanksAndLinebreak("A\n\r")).isEqualTo("A\n\r");
  }

  @Test
  public void testGetTailBeforeLinebreak() {
    assertThat(SourceFunctions.getTailBeforeLinebreak("")).isEmpty();
    assertThat(SourceFunctions.getTailBeforeLinebreak(" ")).isEmpty();
    assertThat(SourceFunctions.getTailBeforeLinebreak("abc")).isEmpty();

    assertThat(SourceFunctions.getTailBeforeLinebreak("\n")).isEmpty();
    assertThat(SourceFunctions.getTailBeforeLinebreak("\n ")).isEqualTo(" ");
    assertThat(SourceFunctions.getTailBeforeLinebreak("\nabc ")).isEqualTo("abc ");

    assertThat(SourceFunctions.getTailBeforeLinebreak(" \n")).isEmpty();
    assertThat(SourceFunctions.getTailBeforeLinebreak(" \n ")).isEqualTo(" ");
    assertThat(SourceFunctions.getTailBeforeLinebreak(" \n\r ")).isEqualTo(" ");
    assertThat(SourceFunctions.getTailBeforeLinebreak("abc\n\rcde")).isEqualTo("cde");

  }
}
