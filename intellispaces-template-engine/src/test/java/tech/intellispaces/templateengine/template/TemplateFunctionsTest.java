package tech.intellispaces.templateengine.template;

import tech.intellispaces.templateengine.element.MarkerFormatTypes;
import tech.intellispaces.templateengine.element.MarkerPrint;
import tech.intellispaces.templateengine.element.StatementForeach;
import tech.intellispaces.templateengine.element.StatementFormat;
import tech.intellispaces.templateengine.element.StatementWhen;
import tech.intellispaces.templateengine.element.TemplateElementTypes;
import tech.intellispaces.templateengine.expression.value.ValueTypes;
import tech.intellispaces.templateengine.source.block.Block;
import tech.intellispaces.templateengine.source.position.Position;
import tech.intellispaces.templateengine.source.position.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemplateFunctions}.
 */
public class TemplateFunctionsTest {

  @Test
  public void testReadBlock_whenEmptySource() {
    // Given
    char[] source = "".toCharArray();
    Position position = Positions.of(0, 1, 1);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("");
    assertThat(block.wording()).isEqualTo("");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(0);
  }

  @Test
  public void testReadBlock_whenSourceHasNotMarkers_andPositionOnSourceBegin() {
    // Given
    char[] source = "Simple text".toCharArray();
    Position position = Positions.of(0, 1, 1);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("Simple text");
    assertThat(block.wording()).isEqualTo("Simple text");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(11);
  }

  @Test
  public void testReadBlock_whenSourceHasNotMarkers_andPositionOnSourceEnd() {
    // Given
    char[] source = "Simple text".toCharArray();
    Position position = Positions.of(11, 1, 12);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("");
    assertThat(block.wording()).isEqualTo("");
    assertThat(block.position().offset()).isEqualTo(11);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(12);
    assertThat(block.length()).isEqualTo(0);
  }

  @Test
  public void testReadBlock_whenPositionMoreThanSourceLength() {
    // Given
    char[] source = "Simple text".toCharArray();
    Position position = Positions.of(12, 1, 13);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNull();
  }

  @Test
  public void testReadBlock_whenSourceStartWithMarker_andPositionOnSourceBegin() {
    // Given
    char[] source = "{{MARKER}} Simple text".toCharArray();
    Position position = Positions.of(0, 1, 1);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isTrue();
    assertThat(block.value()).isEqualTo("MARKER");
    assertThat(block.wording()).isEqualTo("{{MARKER}}");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(10);
  }

  @Test
  public void testReadBlock_whenSourceStartWithMarker_andPositionAfterMarker() {
    // Given
    char[] source = "{{MARKER}} Simple text".toCharArray();
    Position position = Positions.of(10, 1, 11);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo(" Simple text");
    assertThat(block.wording()).isEqualTo(" Simple text");
    assertThat(block.position().offset()).isEqualTo(10);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(11);
    assertThat(block.length()).isEqualTo(12);
  }

  @Test
  public void testReadBlock_whenMarkerPlacedInCenter_andPositionOnSourceBegin() {
    // Given
    char[] source = "Simple {{MARKER}} text".toCharArray();
    Position position = Positions.of(0, 1, 1);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("Simple ");
    assertThat(block.wording()).isEqualTo("Simple ");
    assertThat(block.position().offset()).isEqualTo(0);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(1);
    assertThat(block.length()).isEqualTo(7);
  }

  @Test
  public void testReadBlock_whenMarkerPlacedInCenter_andPositionOnMarkerBegin() {
    // Given
    char[] source = "Simple {{MARKER}} text".toCharArray();
    Position position = Positions.of(7, 1, 8);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isTrue();
    assertThat(block.value()).isEqualTo("MARKER");
    assertThat(block.wording()).isEqualTo("{{MARKER}}");
    assertThat(block.position().offset()).isEqualTo(7);
    assertThat(block.position().row()).isEqualTo(1);
    assertThat(block.position().column()).isEqualTo(8);
    assertThat(block.length()).isEqualTo(10);
  }

  @Test
  public void testReadBlock_whenSourceStartWithDoubleCurlyBraces() {
    // Given
    char[] source = "{{Simple text".toCharArray();
    Position position = Positions.of(0, 1, 1);

    // When
    Block block = TemplateFunctions.readBlock(source, position);

    // Then
    assertThat(block).isNotNull();
    assertThat(block.isMarker()).isFalse();
    assertThat(block.value()).isEqualTo("{{Simple text");
    assertThat(block.wording()).isEqualTo("{{Simple text");
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
    List<Block> blocks = TemplateFunctions.split(source);

    // Then
    assertThat(blocks).hasSize(1);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple text");
    assertThat(blocks.get(0).wording()).isEqualTo("Simple text");
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
    List<Block> blocks = TemplateFunctions.split(source);

    // Then
    assertThat(blocks).hasSize(2);
    assertThat(blocks.get(0).isMarker()).isTrue();
    assertThat(blocks.get(0).value()).isEqualTo("MARKER");
    assertThat(blocks.get(0).wording()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(10);

    assertThat(blocks.get(1).isMarker()).isFalse();
    assertThat(blocks.get(1).value()).isEqualTo("Simple text");
    assertThat(blocks.get(1).wording()).isEqualTo("Simple text");
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
    List<Block> blocks = TemplateFunctions.split(source);

    // Then
    assertThat(blocks).hasSize(3);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple ");
    assertThat(blocks.get(0).wording()).isEqualTo("Simple ");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(7);

    assertThat(blocks.get(1).isMarker()).isTrue();
    assertThat(blocks.get(1).value()).isEqualTo("MARKER");
    assertThat(blocks.get(1).wording()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(1).position().offset()).isEqualTo(7);
    assertThat(blocks.get(1).position().row()).isEqualTo(1);
    assertThat(blocks.get(1).position().column()).isEqualTo(8);
    assertThat(blocks.get(1).length()).isEqualTo(10);

    assertThat(blocks.get(2).isMarker()).isFalse();
    assertThat(blocks.get(2).value()).isEqualTo(" text");
    assertThat(blocks.get(2).wording()).isEqualTo(" text");
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
    List<Block> blocks = TemplateFunctions.split(source);

    // Then
    assertThat(blocks).hasSize(2);
    assertThat(blocks.get(0).isMarker()).isFalse();
    assertThat(blocks.get(0).value()).isEqualTo("Simple text");
    assertThat(blocks.get(0).wording()).isEqualTo("Simple text");
    assertThat(blocks.get(0).position().offset()).isEqualTo(0);
    assertThat(blocks.get(0).position().row()).isEqualTo(1);
    assertThat(blocks.get(0).position().column()).isEqualTo(1);
    assertThat(blocks.get(0).length()).isEqualTo(11);

    assertThat(blocks.get(1).isMarker()).isTrue();
    assertThat(blocks.get(1).value()).isEqualTo("MARKER");
    assertThat(blocks.get(1).wording()).isEqualTo("{{MARKER}}");
    assertThat(blocks.get(1).position().offset()).isEqualTo(11);
    assertThat(blocks.get(1).position().row()).isEqualTo(1);
    assertThat(blocks.get(1).position().column()).isEqualTo(12);
    assertThat(blocks.get(1).length()).isEqualTo(10);
  }

  @Test
  public void testParseTemplate_whenMarkerPrintAndBooleanLiteral() throws Exception {
    // Given
    String source = "{{print true}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("true");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isLiteral()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Boolean);
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().asBoolean().get()).isTrue();
  }

  @Test
  public void testParseTemplate_whenMarkerPrintAndIntegerLiteral() throws Exception {
    // Given
    String source = "{{print  123\t}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("123");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isLiteral()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(123);
  }

  @Test
  public void testParseTemplate_whenMarkerPrintAndRealLiteral() throws Exception {
    // Given
    String source = "{{print  \t3.14  }}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("3.14");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isLiteral()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Real);
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().asReal().get()).isEqualTo(3.14);
  }

  @Test
  public void testParseTemplate_whenMarkerPrintAndStringLiteral() throws Exception {
    // Given
    String source = "{{print \" abc \" }}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("\" abc \"");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isLiteral()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.String);
    assertThat(markerPrint.outputExpression().operands().get(0).asLiteral().value().asString().get()).isEqualTo(" abc ");
  }

  @Test
  public void testParseTemplate_whenMarkerPrintAndVariable() throws Exception {
    // Given
    String source = "{{print $varName}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("$varName");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isVariable()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asVariable().name()).isEqualTo("varName");
  }

  @Test
  public void testParseTemplate_whenShortMarkerPrint() throws Exception {
    // Given
    String source = "{{$varName}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("$varName");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isVariable()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asVariable().name()).isEqualTo("varName");
  }

  @Test
  public void testParseTemplate_whenShortMarkerPrintAndBlankChars() throws Exception {
    // Given
    String source = "{{  $varName  }}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.MarkerPrint);

    MarkerPrint markerPrint = (MarkerPrint) template.elements().get(0);
    assertThat(markerPrint.outputExpression().statement()).isEqualTo("$varName");
    assertThat(markerPrint.outputExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    assertThat(markerPrint.outputExpression().operands()).hasSize(1);
    assertThat(markerPrint.outputExpression().operands().get(0).isVariable()).isTrue();
    assertThat(markerPrint.outputExpression().operands().get(0).asVariable().name()).isEqualTo("varName");
  }

  @Test
  public void testParseTemplate_whenStatementFormat_empty() throws Exception {
    // Given
    String source = "{{format nobr}}{{end}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.StatementFormat);

    StatementFormat statementFormat = (StatementFormat) template.elements().get(0);
    assertThat(statementFormat.types()).isEqualTo(List.of(MarkerFormatTypes.nobr));
    assertThat(statementFormat.subElements()).isEmpty();
  }

  @Test
  public void testParseTemplate_whenStatementForeach_empty() throws Exception {
    // Given
    String source = "{{for item : $listVar}}{{end}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.StatementForeach);

    StatementForeach statementForeach = (StatementForeach) template.elements().get(0);
    assertThat(statementForeach.itemName()).isEqualTo("item");

    Assertions.assertThat(statementForeach.collectionExpression().statement()).isEqualTo("$listVar");
    Assertions.assertThat(statementForeach.collectionExpression().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    Assertions.assertThat(statementForeach.collectionExpression().operands()).hasSize(1);
    Assertions.assertThat(statementForeach.collectionExpression().operands().get(0).isVariable()).isTrue();
    Assertions.assertThat(statementForeach.collectionExpression().operands().get(0).asVariable().name()).isEqualTo("listVar");
    assertThat(statementForeach.subElements()).isEmpty();
  }

  @Test
  public void testParseTemplate_whenStatementWhen_oneEmptyBranch() throws Exception {
    // Given
    String source = "{{when true}}{{end}}";

    // When
    Template template = TemplateFunctions.parseTemplate(source);

    // Then
    Assertions.assertThat(template.elements()).hasSize(1);
    Assertions.assertThat(template.elements().get(0).type()).isEqualTo(TemplateElementTypes.StatementWhen);

    StatementWhen statementWhen = (StatementWhen) template.elements().get(0);
    assertThat(statementWhen.branches()).hasSize(1);
    Assertions.assertThat(statementWhen.branches().get(0).condition().statement()).isEqualTo("true");
    Assertions.assertThat(statementWhen.branches().get(0).condition().preparedStatement()).isEqualTo(
        "(tech.intellispaces.templateengine.expression.value.Value) (operands[0])");
    Assertions.assertThat(statementWhen.branches().get(0).condition().operands()).hasSize(1);
    Assertions.assertThat(statementWhen.branches().get(0).condition().operands().get(0).isLiteral()).isTrue();
    Assertions.assertThat(statementWhen.branches().get(0).condition().operands().get(0).asLiteral().value().asBoolean().get()).isTrue();
    assertThat(statementWhen.branches().get(0).subElements()).isEmpty();

    assertThat(statementWhen.defaultBranch()).isNull();
  }
}
