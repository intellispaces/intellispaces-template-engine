package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.NotApplicableOperationException;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link MapValueBuilder}.
 */
public class MapValueTest {

  @Test
  public void testTypename() throws Exception {
    assertThat(MapValues.of(1, 2).typename().get()).isEqualTo(ValueTypes.Map.typename());
  }

  @Test
  public void testAsBoolean() throws Exception {
    // Given
    MapValue mapValue = MapValues.of("true", true);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      boolean expectedValue = true;
      castFunctions.when(() -> ValueFunctions.castToBoolean(mapValue)).thenReturn(expectedValue);

      // When
      BooleanValue booleanValue = mapValue.asBoolean();

      // Then
      assertThat(booleanValue.get()).isTrue();
      castFunctions.verify(() -> ValueFunctions.castToBoolean(same(mapValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsInteger() throws Exception {
    // Given
    MapValue mapValue = MapValues.of("123", 123);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      int expectedValue = 123;
      castFunctions.when(() -> ValueFunctions.castToInteger(mapValue)).thenReturn(expectedValue);

      // When
      IntegerValue integerValue = mapValue.asInteger();

      // Then
      assertThat(integerValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToInteger(same(mapValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsReal() throws Exception {
    // Given
    MapValue mapValue = MapValues.of("3.14", 3.14);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      double expectedValue = 3.14;
      castFunctions.when(() -> ValueFunctions.castToReal(mapValue)).thenReturn(expectedValue);

      // When
      RealValue realValue = mapValue.asReal();

      // Then
      assertThat(realValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToReal(same(mapValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAString() throws Exception {
    // Given
    MapValue mapValue = MapValues.of("abc", 1);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      String expectedValue = "abc";
      castFunctions.when(() -> ValueFunctions.castToString(mapValue)).thenReturn(expectedValue);

      // When
      StringValue stringValue = mapValue.asString();

      // Then
      assertThat(stringValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToString(same(mapValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws Exception {
    // Given
    MapValue mapValue = MapValues.of("abc", 1);
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      List<?> expectedValue = new ArrayList<>();
      castFunctions.when(() -> ValueFunctions.castToList(mapValue)).thenReturn(expectedValue);

      // When
      ListValue listValue = mapValue.asList();

      // Then
      Assertions.assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToList(same(mapValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    MapValue mapValue = MapValues.of(1, 2);
    assertThat(mapValue.asMap()).isSameAs(mapValue);
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(MapValues.empty().eq(MapValues.empty()).get()).isTrue();

    assertThat(MapValues.of(1, "a").eq(MapValues.of(1, "a")).get()).isTrue();
    assertThat(MapValues.of(1, "a").eq(MapValues.of(1, "b")).get()).isFalse();

    assertThat(MapValues.of(1, "a").eq(BooleanValues.of(true)).get()).isFalse();
    assertThat(MapValues.of(1, "a").eq(IntegerValues.of(1)).get()).isFalse();
    assertThat(MapValues.of(1, "a").eq(RealValues.of(1.2)).get()).isFalse();
    assertThat(MapValues.of(1, "a").eq(StringValues.of("a")).get()).isFalse();

    assertThat(MapValues.of(BooleanValues.of(true), IntegerValues.of(1)).eq(BooleanValues.of(true)).get()).isFalse();

    assertThat(MapValues.of(1, "a").eq(VoidValues.get()).get()).isFalse();
    assertThat(MapValues.of("", "").eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() throws Exception {
    assertThat(MapValues.of(0, 0).isVoid().get()).isFalse();
    assertThat(MapValues.of("", "").isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() throws Exception {
    assertThat(MapValues.empty().isEmpty().get()).isTrue();
    assertThat(MapValues.build().value(new LinkedHashMap<>()).get().isEmpty().get()).isTrue();
    assertThat(MapValues.of(0, 0).isEmpty().get()).isFalse();
  }

  @Test
  public void testIsNotEmpty() throws Exception {
    assertThat(MapValues.empty().isNotEmpty().get()).isFalse();
    assertThat(MapValues.build().value(new LinkedHashMap<>()).get().isNotEmpty().get()).isFalse();
    assertThat(MapValues.of(0, 0).isNotEmpty().get()).isTrue();
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type map. Expected string");
  }

  @Test
  public void testIsNotBlank() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isNotBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotBlank' is not applicable for value type map. Expected string");
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThatThrownBy(() -> MapValues.of(1, "a").capitalizeFirstLetter())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'capitalizeFirstLetter' is not applicable for value type map. Expected string");
  }

  @Test
  public void testInvert() {
    assertThatThrownBy(() -> MapValues.of(1, "a").invert())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'invert' is not applicable for value type map. Expected boolean, integer or real");
  }

  @Test
  public void testGet() throws Exception {
    MapValue mapValue = MapValues.of(1, "a", 2, "b", 3, "c");

    Value value1 = mapValue.get(IntegerValues.of(2));
    assertThat(value1.isVoid().get()).isFalse();
    assertThat(value1.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(value1)).isEqualTo("b");
    assertThatThrownBy(value1::index)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
    assertThatThrownBy(value1::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(value1::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value value2 = mapValue.get(IntegerValues.of(5));
    assertThat(value2.isVoid().get()).isTrue();
  }

  @Test
  public void testFind() {
    assertThatThrownBy(() -> MapValues.of(1, "a").find(IntegerValues.of(0)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'find' is not applicable for value type map. Expected string or list");
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> MapValues.of(1, "a").index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> MapValues.of(1, "a").isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
