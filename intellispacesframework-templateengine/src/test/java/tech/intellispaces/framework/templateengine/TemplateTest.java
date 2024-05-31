package tech.intellispaces.framework.templateengine;

import org.junit.jupiter.api.Test;
import tech.intellispaces.framework.templateengine.template.Template;
import tech.intellispaces.framework.commons.resource.ResourceFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemplateEngine}.
 */
public class TemplateTest {

  @Test
  public void testDemo() throws Exception {
    // Given
    String source = ResourceFunctions.readResourceAsString(TemplateTest.class, "/demo.template").orElseThrow();
    Map<String, Object> variables = new HashMap<>();
    variables.put("str1", "This is string variable str1");
    variables.put("str2", "abc");
    variables.put("int1", 123);
    variables.put("real1", 123.45);
    variables.put("bool1", true);
    variables.put("bool2", false);
    variables.put("list1", List.of("item1", "item2", "item3"));
    variables.put("map1", Map.of("key1", "value1", "key2", "value2", "key3", "value3"));

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo(ResourceFunctions.readResourceAsString(TemplateTest.class, "/demo.result").orElseThrow());
  }

  @Test
  public void testTemplate_whenOneLineText() throws Exception {
    // Given
    String source = "One-line simple text template.";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo(source);
  }

  @Test
  public void testTemplate_whenSeveralLineText() throws Exception {
    // Given
    String source = """
        Multiline
        simple
        text
        template.
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo(source);
  }

  @Test
  public void testTemplate_whenMarkerPrint_andEmptyStringLiteral() throws Exception {
    // Given
    String source = "{{print \"\"}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andBlankStringLiteral() throws Exception {
    // Given
    String source = "{{print \" \"}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo(" ");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andStringLiteral() throws Exception {
    // Given
    String source = "{{print \"abc\"}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andStringLiteral_andCapitalizeFirstLetterOperation() throws Exception {
    // Given
    String source = "{{print \"abc\".capitalizeFirstLetter()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("Abc");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andIntegerLiteral() throws Exception {
    // Given
    String source = "{{print 123}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("123");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andIntegerLiteral_andInvertOperation() throws Exception {
    // Given
    String source = "{{print 123.invert()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("-123");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andRealLiteral() throws Exception {
    // Given
    String source = "{{print 3.14}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("3.14");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andRealLiteral_andEqOperation() throws Exception {
    // Given
    String source = "{{print 3.14.eq($varName)}}";
    Map<String, Object> variables = Map.of("varName", 3);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("false");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andRealLiteral_andInvertOperation() throws Exception {
    // Given
    String source = "{{print 3.14.invert()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("-3.14");
  }

  @Test
  public void testTemplate_whenMarkerPrint_whenStringVariable() throws Exception {
    // Given
    String source = "{{print $name}}";
    Map<String, Object> variables = Map.of("name", "abc");

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("abc");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andListLiteral() throws Exception {
    // Given
    String source = "{{print [1, 2, 3]}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("[1,2,3]");
  }

  @Test
  public void testTemplate_whenMarkerPrint_andMapLiteral() throws Exception {
    // Given
    String source = "{{print [1 : \"a\", 2 : \"b\", 3 : \"c\"]}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("[1:\"a\",2:\"b\",3:\"c\"]");
  }

  @Test
  public void testTemplate_whenMarkerPrintShort_whenStringVariable() throws Exception {
    // Given
    String source = "{{$name}}";
    Map<String, Object> variables = Map.of("name", "abc");

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("abc");
  }

  @Test
  public void testTemplate_whenMarkerPrint_whenIntegerVariable() throws Exception {
    // Given
    String source = "{{print $var1}}";
    Map<String, Object> variables = Map.of("var1", 123);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("123");
  }

  @Test
  public void testTemplate_whenMarkerPrintShort_whenIntegerVariable() throws Exception {
    // Given
    String source = "{{$var1}}";
    Map<String, Object> variables = Map.of("var1", 123);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("123");
  }

  @Test
  public void testTemplate_whenMarkerPrint_whenRealVariable() throws Exception {
    // Given
    String source = "{{print $var1}}";
    Map<String, Object> variables = Map.of("var1", 3.14);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("3.14");
  }

  @Test
  public void testTemplate_whenMarkerPrintShort_whenRealVariable() throws Exception {
    // Given
    String source = "{{$var1}}";
    Map<String, Object> variables = Map.of("var1", 3.14);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("3.14");
  }

  @Test
  public void testTemplate_whenMarkerSetAndPrint_andStringVariable() throws Exception {
    // Given
    String source = "{{set var1 = \"abc\"}}{{print $var1}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc");
  }

  @Test
  public void testTemplate_whenMarkerSetAndPrint_andIntegerVariable() throws Exception {
    // Given
    String source = "{{set var1 = 123}}{{print $var1}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("123");
  }

  @Test
  public void testTemplate_whenMarkerSetAndPrint_andRealVariable() throws Exception {
    // Given
    String source = "{{set var1 = 3.14}}{{print $var1}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("3.14");
  }

  @Test
  public void testTemplate_whenOperationIsEmpty_andString() throws Exception {
    // Given
    String source = "{{print \"\".isEmpty()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("true");
  }

  @Test
  public void testTemplate_whenOperationIsBlank() throws Exception {
    // Given
    String source = "{{print \" \".isBlank()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("true");
  }

  @Test
  public void testTemplate_whenOperationCapitalizeFirstLetter() throws Exception {
    // Given
    String source = "{{print \"abc\".capitalizeFirstLetter()}}";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("Abc");
  }

  @Test
  public void testTemplate_whenFormatStatement() throws Exception {
    // Given
    String source = """
        {{format nobr}}
        abc
        {{print $var1}}
        def
        {{end}}
        """;
    Map<String, Object> variables = Map.of("var1", 123);

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("abc123def");
  }

  @Test
  public void testTemplate_whenForeachStatement() throws Exception {
    // Given
    String source = "{{for item : $list}}{{print $item}}{{end}}";
    Map<String, Object> variables = Map.of("list", List.of("a", "b", "c"));

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, variables);

    // Then
    assertThat(result).isEqualTo("abc");
  }

  @Test
  public void testTemplate_whenWhenStatement_andOneBranch() throws Exception {
    // Given
    String source = """
      {{when true}}
        {{print 1}}
      {{end}}""";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("  1\n");
  }

  @Test
  public void testTemplate_whenWhenStatement_andTwoBranches_andFirstTrue() throws Exception {
    // Given
    String source = """
      {{when true}}
        {{print 1}}
      {{else}}
        {{print 2}}
      {{end}}""";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("  1\n");
  }

  @Test
  public void testTemplate_whenWhenStatement_andTwoBranches_andSecondTrue() throws Exception {
    // Given
    String source = """
      {{when false}}
        {{print 1}}
      {{else}}
        {{print 2}}
      {{end}}""";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("  2\n");
  }

  @Test
  public void testTemplate_whenWhenStatement_andThreeBranches() throws Exception {
    // Given
    String source = """
      {{when $str.eq("a")}}
        {{print 1}}
      {{else when $str.eq("b")}}
        {{print 2}}
      {{else}}
        {{print 3}}
      {{end}}""";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of("str", "b"));

    // Then
    assertThat(result).isEqualTo("  2\n");
  }

  @Test
  public void testTemplate_whenNestedWhenStatements() throws Exception {
    // Given
    String source = """
      {{when true}}
        {{when false}}
          {{print 1}}
        {{else}}
          {{print 2}}
        {{end}}
      {{else}}
        {{print 3}}
      {{end}}""";

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("    2\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case1() throws Exception {
    // Given
    String source = """
        {{set var1 = 123}}
        {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("123\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case2() throws Exception {
    // Given
    String source = """
        abc
        {{set var1 = "def"}}
        {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc\ndef\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case3() throws Exception {
    // Given
    String source = """
        abc
           {{set var1 = "def"}}
        {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc\ndef\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case4() throws Exception {
    // Given
    String source = """
        abc
        {{set var1 = "def"}}
           {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc\n   def\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case5() throws Exception {
    // Given
    String source = """
        {{print "abc"}}
        {{set var1 = "def"}}
        {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc\ndef\n");
  }

  @Test
  public void testTemplate_whenSetMarkerIsOneInRow_case6() throws Exception {
    // Given
    String source = """
        abc{{set var1 = "def"}}
        {{print $var1}}
        """;

    // When
    Template template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo("abc\ndef\n");
  }
}
