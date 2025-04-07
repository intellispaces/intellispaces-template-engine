package tech.intellispaces.templateengine.template;

import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.resource.ResourceFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Templates}.
 */
public class DemoTest {

  @Test
  public void testDemo() throws Exception {
    // Given
    String source = ResourceFunctions.readResourceAsString(DemoTest.class, "/demo.template").orElseThrow();
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
    Template template = Templates.of(source);
    String result = template.resolve(variables);

    // Then
    assertThat(result).isEqualTo(ResourceFunctions.readResourceAsString(DemoTest.class, "/demo.result").orElseThrow());
  }
}
