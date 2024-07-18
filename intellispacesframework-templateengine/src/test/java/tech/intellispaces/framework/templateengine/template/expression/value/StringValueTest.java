package tech.intellispaces.framework.templateengine.template.expression.value;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.intellispaces.framework.templateengine.exception.IrregularValueTypeException;
import tech.intellispaces.framework.templateengine.exception.NotApplicableOperationException;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link StringValues}.
 */
public class StringValueTest {

  @Test
  public void testTypename() {
    assertThat(StringValues.get("abc").typename().get()).isEqualTo(ValueTypes.String.typename());
  }

  @Test
  public void testAsBoolean() throws Exception {
    // Given
    StringValue stringValue = StringValues.get("true");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      boolean expectedValue = true;
      castFunctions.when(() -> ValueFunctions.castToBoolean(stringValue)).thenReturn(expectedValue);

      // When
      BooleanValue booleanValue = stringValue.asBoolean();

      // Then
      assertThat(booleanValue.get()).isTrue();
      castFunctions.verify(() -> ValueFunctions.castToBoolean(same(stringValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsInteger() throws Exception {
    // Given
    StringValue stringValue = StringValues.get("123");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      int expectedValue = 123;
      castFunctions.when(() -> ValueFunctions.castToInteger(stringValue)).thenReturn(expectedValue);

      // When
      IntegerValue integerValue = stringValue.asInteger();

      // Then
      assertThat(integerValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToInteger(same(stringValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsReal() throws Exception {
    // Given
    StringValue stringValue = StringValues.get("3.14");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      double expectedValue = 3.14;
      castFunctions.when(() -> ValueFunctions.castToReal(stringValue)).thenReturn(expectedValue);

      // When
      RealValue realValue = stringValue.asReal();

      // Then
      assertThat(realValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToReal(same(stringValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAString() throws ResolveTemplateException {
    StringValue stringValue = StringValues.get("abc");
    assertThat(stringValue.asString()).isSameAs(stringValue);
  }

  @Test
  public void testAsList() throws Exception {
    // Given
    StringValue stringValue = StringValues.get("abc");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      List<?> expectedValue = List.of();
      castFunctions.when(() -> ValueFunctions.castToList(stringValue)).thenReturn(expectedValue);

      // When
      ListValue listValue = stringValue.asList();

      // Then
      Assertions.assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToList(same(stringValue)), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    // Given
    StringValue stringValue = StringValues.get("abc");
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> ValueFunctions.castToMap(stringValue)).thenReturn(expectedValue);

      // When
      MapValue mapValue = stringValue.asMap();

      // Then
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToMap(same(stringValue)), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").eq(StringValues.get("abc")).get()).isTrue();
    assertThat(StringValues.get("abc").eq(StringValues.get("abcd")).get()).isFalse();
    assertThat(StringValues.get("").eq(StringValues.get("")).get()).isTrue();

    assertThat(StringValues.get("true").eq(BooleanValues.get(true)).get()).isFalse();
    assertThat(StringValues.get("1").eq(IntegerValues.get(1)).get()).isFalse();
    assertThat(StringValues.get("1").eq(RealValues.get(1.0)).get()).isFalse();
    assertThat(StringValues.get("[1]").eq(ListValues.get(1)).get()).isFalse();
    assertThat(StringValues.get("[1:2]").eq(MapValues.get(1, 2)).get()).isFalse();

    assertThat(StringValues.get("abc").eq(VoidValues.get()).get()).isFalse();
    assertThat(StringValues.get("").eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(StringValues.get("abc").isVoid().get()).isFalse();
    assertThat(StringValues.get("void").isVoid().get()).isFalse();
    assertThat(StringValues.get("").isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").isEmpty().get()).isFalse();
    assertThat(StringValues.get("").isEmpty().get()).isTrue();
  }

  @Test
  public void testIsNotEmpty() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").isNotEmpty().get()).isTrue();
    assertThat(StringValues.get("").isNotEmpty().get()).isFalse();
  }

  @Test
  public void testIsBlank() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").isBlank().get()).isFalse();
    assertThat(StringValues.get("").isBlank().get()).isTrue();
    assertThat(StringValues.get(" ").isBlank().get()).isTrue();
    assertThat(StringValues.get("\t\r\n").isBlank().get()).isTrue();
  }

  @Test
  public void testIsNotBlank() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").isNotBlank().get()).isTrue();
    assertThat(StringValues.get("").isNotBlank().get()).isFalse();
    assertThat(StringValues.get(" ").isNotBlank().get()).isFalse();
    assertThat(StringValues.get("\t\r\n").isNotBlank().get()).isFalse();
  }

  @Test
  public void testCapitalizeFirstLetter() throws ResolveTemplateException {
    assertThat(StringValues.get("abc").capitalizeFirstLetter().get()).isEqualTo("Abc");
    assertThat(StringValues.get("").capitalizeFirstLetter().get()).isEqualTo("");
    assertThat(StringValues.get(" \t\r\n").capitalizeFirstLetter().get()).isEqualTo(" \t\r\n");
  }

  @Test
  public void testInvert() {
    assertThatThrownBy(() -> StringValues.get("abc").invert())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'invert' is not applicable for value type string. Expected boolean, integer or real");
  }

  @Test
  public void testGet() throws Exception {
    assertThatThrownBy(() -> StringValues.get("abc").get(BooleanValues.get(true)))
        .isExactlyInstanceOf(IrregularValueTypeException.class)
        .hasMessage("Invalid index type: boolean. Expected integer value");

    Value stringValue = StringValues.get("abc");

    Value element0 = stringValue.get(IntegerValues.get(0));
    assertThat(element0.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element0)).isEqualTo("a");
    assertThat(element0.index().asInteger().get()).isEqualTo(0);
    assertThat(element0.isFirst().asBoolean().get()).isTrue();
    assertThat(element0.isLast().asBoolean().get()).isFalse();

    Value element2 = stringValue.get(IntegerValues.get(2));
    assertThat(element2.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element2)).isEqualTo("c");
    assertThat(element2.index().asInteger().get()).isEqualTo(2);
    assertThat(element2.isFirst().asBoolean().get()).isFalse();
    assertThat(element2.isLast().asBoolean().get()).isTrue();

    Value negativeElement = stringValue.get(IntegerValues.get(-1));
    assertThat(negativeElement.type()).isEqualTo(ValueTypes.Void);
    assertThat(negativeElement.index().asInteger().get()).isEqualTo(-1);
    assertThatThrownBy(negativeElement::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(negativeElement::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value element3 = stringValue.get(IntegerValues.get(3));
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
  public void testFind_whenLetters() throws Exception {
    StringValue string = StringValues.get("abc");

    Value substringA = string.find(StringValues.get("a"));
    assertThat(substringA.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringA)).isEqualTo("a");
    assertThat(substringA.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(substringA::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringA::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringC = string.find(StringValues.get("c"));
    assertThat(substringC.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringC)).isEqualTo("c");
    assertThat(substringC.index().asInteger().get()).isEqualTo(2);
    assertThatThrownBy(substringC::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringC::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringAbc = string.find(StringValues.get("abc"));
    assertThat(substringAbc.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringAbc)).isEqualTo("abc");
    assertThat(substringAbc.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(substringAbc::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringAbc::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringD = string.find(StringValues.get("d"));
    assertThat(substringD.isVoid().get()).isTrue();

    Value emptySubstring = string.find(StringValues.get(""));
    assertThat(emptySubstring.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(emptySubstring)).isEqualTo("");
    assertThat(emptySubstring.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(emptySubstring::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(emptySubstring::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testFind_whenLettersAndInteger() throws Exception {
    StringValue string = StringValues.get("a1c");

    Value substring1 = string.find(IntegerValues.get(1));
    assertThat(substring1.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring1)).isEqualTo("1");
    assertThat(substring1.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring1::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring1::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring2 = string.find(IntegerValues.get(2));
    assertThat(substring2.isVoid().get()).isTrue();
  }

  @Test
  public void testFind_whenLettersAndReal() throws Exception {
    StringValue string1 = StringValues.get("a3.14c");

    Value substring3p14 = string1.find(RealValues.get(3.14));
    assertThat(substring3p14.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring3p14)).isEqualTo("3.14");
    assertThat(substring3p14.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring3p14::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring3p14::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring3 = string1.find(IntegerValues.get(3));
    assertThat(substring3.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring3)).isEqualTo("3");
    assertThat(substring3.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring3::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring3::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring3p0 = string1.find(RealValues.get(3));
    assertThat(substring3p0.isVoid().get()).isTrue();

    Value substring3p16 = string1.find(RealValues.get(3.16));
    assertThat(substring3p16.isVoid().get()).isTrue();

    StringValue string2 = StringValues.get("a3.0c");
    substring3p0 = string2.find(RealValues.get(3));
    assertThat(substring3p0.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring3p0)).isEqualTo("3.0");
    assertThat(substring3p0.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring3p0::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring3p0::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> StringValues.get("abc").index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> StringValues.get("abc").isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> StringValues.get("abc").isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> StringValues.get("abc").isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> StringValues.get("abc").isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
