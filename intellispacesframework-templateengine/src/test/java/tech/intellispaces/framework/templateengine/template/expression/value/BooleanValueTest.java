package tech.intellispaces.framework.templateengine.template.expression.value;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.intellispaces.framework.templateengine.exception.NotApplicableOperationException;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link BooleanValueBuilder}.
 */
public class BooleanValueTest {

  @Test
  public void testTypename() {
    assertThat(BooleanValueBuilder.build(true).typename().get()).isEqualTo(ValueTypes.Boolean.typename());
  }

  @Test
  public void testAsBoolean() throws ResolveTemplateException {
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    assertThat(booleanValue.asBoolean()).isSameAs(booleanValue);
  }

  @Test
  public void testAsInteger() throws Exception {
    // Given
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      int expectedValue = 123;
      castFunctions.when(() -> ValueFunctions.castToInteger(booleanValue)).thenReturn(expectedValue);

      // When
      IntegerValue integerValue = booleanValue.asInteger();

      // Then
      assertThat(integerValue.get()).isEqualTo(123);
      castFunctions.verify(() -> ValueFunctions.castToInteger(same(booleanValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsReal() throws Exception {
    // Given
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      double expectedValue = 3.14;
      castFunctions.when(() -> ValueFunctions.castToReal(booleanValue)).thenReturn(expectedValue);

      // When
      RealValue realValue = booleanValue.asReal();

      // Then
      assertThat(realValue.get()).isEqualTo(3.14);
      castFunctions.verify(() -> ValueFunctions.castToReal(same(booleanValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsString() throws Exception {
    // Given
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      String expectedValue = "true";
      castFunctions.when(() -> ValueFunctions.castToString(booleanValue)).thenReturn(expectedValue);

      // When
      StringValue stringValue = booleanValue.asString();

      // Then
      assertThat(stringValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToString(same(booleanValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws Exception {
    // Given
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      List<?> expectedValue = List.of();
      castFunctions.when(() -> ValueFunctions.castToList(booleanValue)).thenReturn(expectedValue);

      // When
      ListValue listValue = booleanValue.asList();

      // Then
      Assertions.assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToList(same(booleanValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    // Given
    BooleanValue booleanValue = BooleanValueBuilder.build(true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> ValueFunctions.castToMap(booleanValue)).thenReturn(expectedValue);

      // When
      MapValue mapValue = booleanValue.asMap();

      // Then
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToMap(same(booleanValue)), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(BooleanValueBuilder.build(true).eq(BooleanValueBuilder.build(true)).get()).isTrue();
    assertThat(BooleanValueBuilder.build(false).eq(BooleanValueBuilder.build(false)).get()).isTrue();
    assertThat(BooleanValueBuilder.build(true).eq(BooleanValueBuilder.build(false)).get()).isFalse();

    assertThat(BooleanValueBuilder.build(true).eq(StringValueBuilder.build("true")).get()).isFalse();
    assertThat(BooleanValueBuilder.build(true).eq(IntegerValueBuilder.build(1)).get()).isFalse();
    assertThat(BooleanValueBuilder.build(true).eq(RealValueBuilder.build(1.0)).get()).isFalse();
    assertThat(BooleanValueBuilder.build(true).eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(BooleanValueBuilder.build(true).isVoid().get()).isFalse();
    assertThat(BooleanValueBuilder.build(false).isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isEmpty())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isEmpty' is not applicable for value type boolean. Expected string, list or map");
  }

  @Test
  public void testIsNotEmpty() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isNotEmpty())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotEmpty' is not applicable for value type boolean. Expected string, list or map");
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type boolean. Expected string");
  }

  @Test
  public void testIsNotBlank() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isNotBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotBlank' is not applicable for value type boolean. Expected string");
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).capitalizeFirstLetter())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'capitalizeFirstLetter' is not applicable for value type boolean. Expected string");
  }

  @Test
  public void testInvert() throws ResolveTemplateException {
    assertThat(BooleanValueBuilder.build(true).invert().get()).isFalse();
    assertThat(BooleanValueBuilder.build(false).invert().get()).isTrue();
  }

  @Test
  public void testGet() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).get(IntegerValueBuilder.build(0)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'get' is not applicable for value type boolean. Expected map, list or string");
  }

  @Test
  public void testFind() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).find(BooleanValueBuilder.build(true)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'find' is not applicable for value type boolean. Expected string or list");
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> BooleanValueBuilder.build(true).isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
