package intellispaces.templateengine;

import intellispaces.commons.ResourceFunctions;
import intellispaces.templateengine.model.TextTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemplateEngine}.
 */
public class TemplateEngineTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "/TextOneLine.template",
      "/TextMultiLine.template"})
  public void testText(String templateName) throws Exception {
    // Given
    String source = ResourceFunctions.readResourceAsString(this.getClass(), templateName).orElseThrow();

    // When
    TextTemplate template = TemplateEngine.parseTemplate(source);
    String result = TemplateEngine.resolveTemplate(template, Map.of());

    // Then
    assertThat(result).isEqualTo(source);
  }
}
