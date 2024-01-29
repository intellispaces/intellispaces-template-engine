package intellispaces.templateengine.function.parse;

import intellispaces.templateengine.builder.TextPositionBuilder;
import intellispaces.templateengine.model.TextBlock;
import intellispaces.templateengine.model.TextPosition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ParseFunctions}.
 */
public class ParseFunctionsTest {

  @Test
  public void testReadBlock_whenEmptySource() {
    // Given
    char[] source = "".toCharArray();
    TextPosition position = TextPositionBuilder.build(0, 1, 1);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("");
    assertThat(block.text()).isEqualTo("");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(0);
  }

  @Test
  public void testReadBlock_whenSourceHasNotMarkers_andPositionOnSourceBegin() {
    // Given
    char[] source = "Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(0, 1, 1);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("Simple text");
    assertThat(block.text()).isEqualTo("Simple text");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(11);
  }

  @Test
  public void testReadBlock_whenSourceHasNotMarkers_andPositionOnSourceEnd() {
    // Given
    char[] source = "Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(11, 1, 12);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("");
    assertThat(block.text()).isEqualTo("");
    assertThat(block.position().offset()).isEqualTo(11);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(12);
    assertThat(block.length()).isEqualTo(0);
  }

  @Test
  public void testReadBlock_whenPositionMoreThanSourceLength() {
    // Given
    char[] source = "Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(12, 1, 13);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNull();
  }

  @Test
  public void testReadBlock_whenSourceStartWithMarker_andPositionOnSourceBegin() {
    // Given
    char[] source = "{{MARKER}} Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(0, 1, 1);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isTrue();
    assertThat(block.value()).isEqualTo("MARKER");
    assertThat(block.text()).isEqualTo("{{MARKER}}");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(10);
  }

  @Test
  public void testReadBlock_whenSourceStartWithMarker_andPositionAfterMarker() {
    // Given
    char[] source = "{{MARKER}} Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(10, 1, 11);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo(" Simple text");
    assertThat(block.text()).isEqualTo(" Simple text");
    assertThat(block.position().offset()).isEqualTo(10);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(11);
    assertThat(block.length()).isEqualTo(12);
  }

  @Test
  public void testReadBlock_whenMarkerPlacedInCenter_andPositionOnSourceBegin() {
    // Given
    char[] source = "Simple {{MARKER}} text".toCharArray();
    TextPosition position = TextPositionBuilder.build(0, 1, 1);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("Simple ");
    assertThat(block.text()).isEqualTo("Simple ");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(7);
  }

  @Test
  public void testReadBlock_whenMarkerPlacedInCenter_andPositionOnMarkerBegin() {
    // Given
    char[] source = "Simple {{MARKER}} text".toCharArray();
    TextPosition position = TextPositionBuilder.build(7, 1, 8);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isTrue();
    assertThat(block.value()).isEqualTo("MARKER");
    assertThat(block.text()).isEqualTo("{{MARKER}}");
    assertThat(block.position().offset()).isEqualTo(7);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(8);
    assertThat(block.length()).isEqualTo(10);
  }

  @Test
  public void testReadBlock_whenSourceStartWithDoubleCurlyBraces() {
    // Given
    char[] source = "{{Simple text".toCharArray();
    TextPosition position = TextPositionBuilder.build(0, 1, 1);

    // When
    TextBlock block = ParseFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("{{Simple text");
    assertThat(block.text()).isEqualTo("{{Simple text");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(13);
  }

  @Test
  public void testSplitByMarkers_whenNoMarkers() {
    // Given
    String source = "Simple text";

    // When
    List<TextBlock> blocks = ParseFunctions.splitByMarkers(source);

    // Then
    assertThat(blocks).hasSize(1);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple text");
    assertThat(blocks.get(0).text()).isEqualTo("Simple text");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(11);
  }

  @Test
  public void testSplitByMarkers_whenOneMarkerOnSourceBegin() {
    // Given
    String source = "{{MARKER}}Simple text";

    // When
    List<TextBlock> blocks = ParseFunctions.splitByMarkers(source);

    // Then
    assertThat(blocks).hasSize(2);
    assertThat(blocks.get(0).isMarker()).isTrue();
    assertThat(blocks.get(0).value()).isEqualTo("MARKER");
    assertThat(blocks.get(0).text()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(10);

    assertThat(blocks.get(1).isMarker()).isFalse();
    assertThat(blocks.get(1).value()).isEqualTo("Simple text");
    assertThat(blocks.get(1).text()).isEqualTo("Simple text");
    assertThat(blocks.get(1).position().offset()).isEqualTo(10);
    assertThat(blocks.get(1).position().row()).isEqualTo(1);
    assertThat(blocks.get(1).position().column()).isEqualTo(11);
    assertThat(blocks.get(1).length()).isEqualTo(11);
  }

  @Test
  public void testSplitByMarkers_whenOneMarkerInCenterSource() {
    // Given
    String source = "Simple {{MARKER}} text";

    // When
    List<TextBlock> blocks = ParseFunctions.splitByMarkers(source);

    // Then
    assertThat(blocks).hasSize(3);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple ");
    assertThat(blocks.get(0).text()).isEqualTo("Simple ");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(7);

    assertThat(blocks.get(1).isMarker()).isTrue();
    assertThat(blocks.get(1).value()).isEqualTo("MARKER");
    assertThat(blocks.get(1).text()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(1).position().offset()).isEqualTo(7);
    assertThat(blocks.get(1).position().row()).isEqualTo(1);
    assertThat(blocks.get(1).position().column()).isEqualTo(8);
    assertThat(blocks.get(1).length()).isEqualTo(10);

    assertThat(blocks.get(2).isMarker()).isFalse();
    assertThat(blocks.get(2).value()).isEqualTo(" text");
    assertThat(blocks.get(2).text()).isEqualTo(" text");
    assertThat(blocks.get(2).position().offset()).isEqualTo(17);
    assertThat(blocks.get(2).position().row()).isEqualTo(1);
    assertThat(blocks.get(2).position().column()).isEqualTo(18);
    assertThat(blocks.get(2).length()).isEqualTo(5);
  }

  @Test
  public void testSplitByMarkers_whenOneMarkerOnSourceEnd() {
    // Given
    String source = "Simple text{{MARKER}}";

    // When
    List<TextBlock> blocks = ParseFunctions.splitByMarkers(source);

    // Then
    assertThat(blocks).hasSize(2);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple text");
    assertThat(blocks.get(0).text()).isEqualTo("Simple text");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(11);

    assertThat(blocks.get(1).isMarker()).isTrue();
    assertThat(blocks.get(1).value()).isEqualTo("MARKER");
    assertThat(blocks.get(1).text()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(1).position().offset()).isEqualTo(11);
    assertThat(blocks.get(1).position().row()).isEqualTo(1);
    assertThat(blocks.get(1).position().column()).isEqualTo(12);
    assertThat(blocks.get(1).length()).isEqualTo(10);
  }
}
