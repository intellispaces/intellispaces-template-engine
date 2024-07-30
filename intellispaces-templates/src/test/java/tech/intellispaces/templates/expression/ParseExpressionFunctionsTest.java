package tech.intellispaces.templates.expression;

import org.junit.jupiter.api.Test;
import tech.intellispaces.templates.expression.value.ValueTypes;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ParseExpressionFunctions}.
 */
public class ParseExpressionFunctionsTest {

  @Test
  public void testParseExpression_whenEmpty() throws Exception {
    // Given
    String statement = "\"\"";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.String);
    assertThat(expression.operands().get(0).asLiteral().value().asString().get()).isEmpty();
  }

  @Test
  public void testParseExpression_whenVoid() throws Exception {
    // Given
    String statement = "void";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Void);
  }

  @Test
  public void testParseExpression_whenBooleanTrue() throws Exception {
    // Given
    String statement = "true";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Boolean);
    assertThat(expression.operands().get(0).asLiteral().value().asBoolean().get()).isTrue();
  }

  @Test
  public void testParseExpression_whenBooleanFalse() throws Exception {
    // Given
    String statement = "false";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Boolean);
    assertThat(expression.operands().get(0).asLiteral().value().asBoolean().get()).isFalse();
  }

  @Test
  public void testParseExpression_whenStringOneChar() throws Exception {
    // Given
    String statement = "\"a\"";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.String);
    assertThat(expression.operands().get(0).asLiteral().value().asString().get()).isEqualTo("a");
  }

  @Test
  public void testParseExpression_whenStringSeveralChars() throws Exception {
    // Given
    String statement = "\"abc\"";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.String);
    assertThat(expression.operands().get(0).asLiteral().value().asString().get()).isEqualTo("abc");

  }

  @Test
  public void testParseExpression_whenIntegerOneDigit() throws Exception {
    // Given
    String statement = "1";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(1);
  }

  @Test
  public void testParseExpression_whenIntegerSeveralDigits() throws Exception {
    // Given
    String statement = "123";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(123);
  }

  @Test
  public void testParseExpression_whenPositiveIntegerOneDigit() throws Exception {
    // Given
    String statement = "+1";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(1);
  }

  @Test
  public void testParseExpression_whenPositiveIntegerSeveralDigits() throws Exception {
    // Given
    String statement = "+123";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(123);
  }

  @Test
  public void testParseExpression_whenNegativeIntegerOneDigit() throws Exception {
    // Given
    String statement = "-1";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(-1);
  }

  @Test
  public void testParseExpression_whenNegativeIntegerSeveralDigits() throws Exception {
    // Given
    String statement = "-123";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Integer);
    assertThat(expression.operands().get(0).asLiteral().value().asInteger().get()).isEqualTo(-123);
  }

  @Test
  public void testParseExpression_whenReal() throws Exception {
    // Given
    String statement = "3.14";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Real);
    assertThat(expression.operands().get(0).asLiteral().value().asReal().get()).isEqualTo(3.14);
  }

  @Test
  public void testParseExpression_whenNegativeReal() throws Exception {
    // Given
    String statement = "-3.0";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Real);
    assertThat(expression.operands().get(0).asLiteral().value().asReal().get()).isEqualTo(-3.0);
  }

  @Test
  public void testParseExpression_whenPositiveReal() throws Exception {
    // Given
    String statement = "+3.0";

    // When
    Expression expression = ParseExpressionFunctions.parseExpression(statement);

    // Then
    assertThat(expression).isNotNull();
    assertThat(expression.statement()).isEqualTo(statement);
    assertThat(expression.operands()).hasSize(1);
    assertThat(expression.operands().get(0).isLiteral()).isTrue();
    assertThat(expression.operands().get(0).asLiteral().value().type()).isEqualTo(ValueTypes.Real);
    assertThat(expression.operands().get(0).asLiteral().value().asReal().get()).isEqualTo(3.0);
  }
}
