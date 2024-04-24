package intellispaces.templateengine.template.source;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemplateSourceFunctions}.
 */
public class TemplateSourceFunctionsTest {

  @Test
  public void testIsWordChar() {
    assertThat(TemplateSourceFunctions.isWordChar('a')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('A')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('z')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('Z')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('a')).isTrue();

    assertThat(TemplateSourceFunctions.isWordChar('0')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('1')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('2')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('3')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('4')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('5')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('6')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('7')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('8')).isTrue();
    assertThat(TemplateSourceFunctions.isWordChar('9')).isTrue();

    assertThat(TemplateSourceFunctions.isWordChar('_')).isTrue();

    assertThat(TemplateSourceFunctions.isWordChar(' ')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('\t')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('\r')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('\n')).isFalse();

    assertThat(TemplateSourceFunctions.isWordChar('+')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('-')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('*')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('?')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('#')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('%')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('&')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('(')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar(')')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('[')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar(']')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('{')).isFalse();
    assertThat(TemplateSourceFunctions.isWordChar('}')).isFalse();
  }

  @Test
  public void testIsBeginWithLineBreakIgnoreBlanks() {
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r\nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \r\nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks(" \n\r")).isTrue();

    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\n")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\r\nA")).isTrue();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\t\n\r")).isTrue();

    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(TemplateSourceFunctions.isBeginWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testIsEndWithLineBreakIgnoreBlanks() {
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r")).isTrue();

    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n ")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n ")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r ")).isTrue();

    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\t")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\n\t")).isTrue();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\r\t")).isTrue();

    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\nA")).isFalse();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r\nA")).isFalse();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\n\rA")).isFalse();

    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("")).isFalse();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
    assertThat(TemplateSourceFunctions.isEndWithLineBreakIgnoreBlanks("\r")).isFalse();
  }

  @Test
  public void testRemoveLastGaps() {
    assertThat(TemplateSourceFunctions.removeLastGaps("\n")).isEqualTo("\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\r\n")).isEqualTo("\r\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\n\r")).isEqualTo("\n\r");

    assertThat(TemplateSourceFunctions.removeLastGaps("\n ")).isEqualTo("\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\r\n ")).isEqualTo("\r\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\n\r ")).isEqualTo("\n\r");

    assertThat(TemplateSourceFunctions.removeLastGaps("\n\t")).isEqualTo("\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\r\n\t")).isEqualTo("\r\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("\n\r\t")).isEqualTo("\n\r");

    assertThat(TemplateSourceFunctions.removeLastGaps("a\n")).isEqualTo("a\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("b\r\n")).isEqualTo("b\r\n");
    assertThat(TemplateSourceFunctions.removeLastGaps("c\n\r")).isEqualTo("c\n\r");

    assertThat(TemplateSourceFunctions.removeLastGaps("\nA")).isEqualTo("\nA");
    assertThat(TemplateSourceFunctions.removeLastGaps("\r\nA")).isEqualTo("\r\nA");

    assertThat(TemplateSourceFunctions.removeLastGaps("  ")).isEqualTo("");

    assertThat(TemplateSourceFunctions.removeLastGaps("")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeLastGaps("\r")).isEqualTo("\r");
  }

  @Test
  public void testRemoveFirstBlanksAndLineBreak() {
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\r\n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\n\r")).isEqualTo("");

    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak(" \n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak(" \r\n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak(" \n\r")).isEqualTo("");

    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\t\n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\t\r\n")).isEqualTo("");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("\t\n\r")).isEqualTo("");

    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("A\n")).isEqualTo("A\n");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("A\r\n")).isEqualTo("A\r\n");
    assertThat(TemplateSourceFunctions.removeFirstBlanksAndLineBreak("A\n\r")).isEqualTo("A\n\r");
  }
}
