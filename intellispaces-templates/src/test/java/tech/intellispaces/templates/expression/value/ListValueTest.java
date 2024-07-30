package tech.intellispaces.templates.expression.value;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.intellispaces.templates.exception.IrregularValueTypeException;
import tech.intellispaces.templates.exception.NotApplicableOperationException;
import tech.intellispaces.templates.exception.ResolveTemplateException;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link ListValueBuilder}.
 */
public class ListValueTest {

  @Test
  public void testTypename() {
    assertThat(ListValues.of(1).typename().get()).isEqualTo(ValueTypes.List.typename());
  }

  @Test
  public void testAsBoolean() throws Exception {
    // Given
    ListValue listValue = ListValues.of("true");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      boolean expectedValue = true;
      castFunctions.when(() -> ValueFunctions.castToBoolean(listValue)).thenReturn(expectedValue);

      // When
      BooleanValue booleanValue = listValue.asBoolean();

      // Then
      assertThat(booleanValue.get()).isTrue();
      castFunctions.verify(() -> ValueFunctions.castToBoolean(same(listValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsInteger() throws Exception {
    // Given
    ListValue listValue = ListValues.of("123");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      int expectedValue = 123;
      castFunctions.when(() -> ValueFunctions.castToInteger(listValue)).thenReturn(expectedValue);

      // When
      IntegerValue integerValue = listValue.asInteger();

      // Then
      assertThat(integerValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToInteger(same(listValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsReal() throws Exception {
    // Given
    ListValue listValue = ListValues.of("3.14");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      double expectedValue = 3.14;
      castFunctions.when(() -> ValueFunctions.castToReal(listValue)).thenReturn(expectedValue);

      // When
      RealValue realValue = listValue.asReal();

      // Then
      assertThat(realValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToReal(same(listValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAString() throws Exception {
    // Given
    ListValue listValue = ListValues.of("abc");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      String expectedValue = "abc";
      castFunctions.when(() -> ValueFunctions.castToString(listValue)).thenReturn(expectedValue);

      // When
      StringValue stringValue = listValue.asString();

      // Then
      assertThat(stringValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToString(same(listValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws ResolveTemplateException {
    ListValue listValue = ListValues.of(1, 2, 3);
    assertThat(listValue.asList()).isSameAs(listValue);
  }

  @Test
  public void testAsMap() throws Exception {
    // Given
    ListValue listValue = ListValues.of("abc");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> ValueFunctions.castToMap(listValue)).thenReturn(expectedValue);

      // When
      MapValue mapValue = listValue.asMap();

      // Then
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToMap(same(listValue)), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(ListValues.empty().eq(ListValues.empty()).get()).isTrue();

    assertThat(ListValues.of(true).eq(ListValues.of(true)).get()).isTrue();
    assertThat(ListValues.of(false).eq(ListValues.of(false)).get()).isTrue();
    assertThat(ListValues.of(true).eq(ListValues.of(false)).get()).isFalse();

    assertThat(ListValues.of(1).eq(ListValues.of(1)).get()).isTrue();
    assertThat(ListValues.of(1, 2, 3).eq(ListValues.of(1, 2, 3)).get()).isTrue();
    assertThat(ListValues.of(1).eq(ListValues.of(2)).get()).isFalse();

    assertThat(ListValues.of(1.2).eq(ListValues.of(1.2)).get()).isTrue();
    assertThat(ListValues.of(1.2, 2.3, 3.4).eq(ListValues.of(1.2, 2.3, 3.4)).get()).isTrue();
    assertThat(ListValues.of(1.2).eq(ListValues.of(1.3)).get()).isFalse();

    assertThat(ListValues.of("a").eq(ListValues.of("a")).get()).isTrue();
    assertThat(ListValues.of("a", "b", "c").eq(ListValues.of("a", "b", "c")).get()).isTrue();
    assertThat(ListValues.of("a").eq(ListValues.of("c")).get()).isFalse();

    assertThat(ListValues.of(true).eq(BooleanValues.of(true)).get()).isFalse();
    assertThat(ListValues.of(1).eq(IntegerValues.of(1)).get()).isFalse();
    assertThat(ListValues.of(1.2).eq(RealValues.of(1.2)).get()).isFalse();
    assertThat(ListValues.of("a").eq(StringValues.of("a")).get()).isFalse();

    assertThat(ListValues.of(BooleanValues.of(true)).eq(BooleanValues.of(true)).get()).isFalse();
    assertThat(ListValues.of(IntegerValues.of(1)).eq(IntegerValues.of(1)).get()).isFalse();
    assertThat(ListValues.of(RealValues.of(1.2)).eq(RealValues.of(1.2)).get()).isFalse();
    assertThat(ListValues.of(StringValues.of("a")).eq(StringValues.of("a")).get()).isFalse();

    assertThat(ListValues.of(0).eq(VoidValues.get()).get()).isFalse();
    assertThat(ListValues.of("").eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(ListValues.of(0).isVoid().get()).isFalse();
    assertThat(ListValues.of("").isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() throws Exception {
    assertThat(ListValues.empty().isEmpty().get()).isTrue();
    assertThat(ListValues.build().value(List.of()).get().isEmpty().get()).isTrue();
    assertThat(ListValues.of(0).isEmpty().get()).isFalse();
  }

  @Test
  public void testIsNotEmpty() throws Exception {
    assertThat(ListValues.empty().isNotEmpty().get()).isFalse();
    assertThat(ListValues.build().value(List.of()).get().isNotEmpty().get()).isFalse();
    assertThat(ListValues.of(0).isNotEmpty().get()).isTrue();
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(() -> ListValues.of(1, 2, 3).isBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type list. Expected string");
  }

  @Test
  public void testIsNotBlank() {
    assertThatThrownBy(() -> ListValues.of(1, 2, 3).isNotBlank())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotBlank' is not applicable for value type list. Expected string");
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThatThrownBy(() -> ListValues.of(1, 2, 3).capitalizeFirstLetter())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'capitalizeFirstLetter' is not applicable for value type list. Expected string");
  }

  @Test
  public void testInvert() {
    assertThatThrownBy(() -> ListValues.of(1, 2, 3).invert())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'invert' is not applicable for value type list. Expected boolean, integer or real");
  }

  @Test
  public void testGet() throws Exception {
    assertThatThrownBy(() -> ListValues.of(1, 2, 3).get(BooleanValues.of(true)))
        .isExactlyInstanceOf(IrregularValueTypeException.class)
        .hasMessage("Invalid index type: boolean. Expected integer value");

    ListValue listValue = ListValues.of("a", "b", "c");

    Value element0 = listValue.get(IntegerValues.of(0));
    assertThat(element0.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element0)).isEqualTo("a");
    assertThat(element0.index().asInteger().get()).isEqualTo(0);
    assertThat(element0.isFirst().asBoolean().get()).isTrue();
    assertThat(element0.isLast().asBoolean().get()).isFalse();

    Value element2 = listValue.get(IntegerValues.of(2));
    assertThat(element2.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element2)).isEqualTo("c");
    assertThat(element2.index().asInteger().get()).isEqualTo(2);
    assertThat(element2.isFirst().asBoolean().get()).isFalse();
    assertThat(element2.isLast().asBoolean().get()).isTrue();

    Value negativeElement = listValue.get(IntegerValues.of(-1));
    assertThat(negativeElement.type()).isEqualTo(ValueTypes.Void);
    assertThat(negativeElement.index().asInteger().get()).isEqualTo(-1);
    assertThatThrownBy(negativeElement::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(negativeElement::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value element3 = listValue.get(IntegerValues.of(3));
    assertThat(element3.type()).isEqualTo(ValueTypes.Void);
    assertThat(element3.index().asInteger().get()).isEqualTo(3);
    assertThatThrownBy(element3::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(element3::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testFind_whenStringList() throws ResolveTemplateException {
    ListValue list = ListValues.of("a", "b", "c");

    assertThat(list.find(StringValues.of("")).isVoid().get()).isTrue();

    Value stringA = list.find(StringValues.of("a"));
    assertThat(stringA.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(stringA)).isEqualTo(List.of("a"));
    assertThat(stringA.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(stringA::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(stringA::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value stringC = list.find(StringValues.of("c"));
    assertThat(stringC.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(stringC)).isEqualTo(List.of("c"));
    assertThat(stringC.index().asInteger().get()).isEqualTo(2);
    assertThatThrownBy(stringC::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(stringC::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value stringD = list.find(StringValues.of("d"));
    assertThat(stringD.isVoid().get()).isTrue();

    Value stringAbc = list.find(StringValues.of("abc"));
    assertThat(stringAbc.isVoid().get()).isTrue();

    Value sublistA = list.find(ListValues.of("a"));
    assertThat(sublistA.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(sublistA)).isEqualTo(List.of("a"));
    assertThat(sublistA.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(sublistA::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(sublistA::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value sublistBc = list.find(ListValues.of("b", "c"));
    assertThat(sublistBc.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(sublistBc)).isEqualTo(List.of("b", "c"));
    assertThat(sublistBc.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(sublistBc::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(sublistBc::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value sublistAbc = list.find(ListValues.of("a", "b", "c"));
    assertThat(sublistAbc.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(sublistAbc)).isEqualTo(List.of("a", "b", "c"));
    assertThat(sublistAbc.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(sublistAbc::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(sublistAbc::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value sublistAc = list.find(ListValues.of("a", "c"));
    assertThat(sublistAc.isVoid().get()).isTrue();
  }

  @Test
  public void testFind_whenComplexList() throws ResolveTemplateException {
    ListValue list = ListValues.of(StringValues.of("a"), IntegerValues.of(1), StringValues.of("c"));

    Value value1 = list.find(IntegerValues.of(1));
    assertThat(value1.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(value1)).isEqualTo(List.of(1));
    assertThat(value1.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(value1::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(value1::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value value2 = list.find(IntegerValues.of(2));
    assertThat(value2.isVoid().get()).isTrue();
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> ListValues.of("a", "b", "c").index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> ListValues.of("a", "b", "c").isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> ListValues.of("a", "b", "c").isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> ListValues.of("a", "b", "c").isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> ListValues.of("a", "b", "c").isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
