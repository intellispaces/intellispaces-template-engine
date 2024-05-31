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
 * Tests for {@link StringValueBuilder}.
 */
public class StringValueTest {

  @Test
  public void testTypename() {
    assertThat(StringValueBuilder.build("abc").typename().get()).isEqualTo(ValueTypes.String.typename());
  }

  @Test
  public void testAsBoolean() throws Exception {
    // Given
    StringValue stringValue = StringValueBuilder.build("true");
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
    StringValue stringValue = StringValueBuilder.build("123");
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
    StringValue stringValue = StringValueBuilder.build("3.14");
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
    StringValue stringValue = StringValueBuilder.build("abc");
    assertThat(stringValue.asString()).isSameAs(stringValue);
  }

  @Test
  public void testAsList() throws Exception {
    // Given
    StringValue stringValue = StringValueBuilder.build("abc");
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
    StringValue stringValue = StringValueBuilder.build("abc");
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
    assertThat(StringValueBuilder.build("abc").eq(StringValueBuilder.build("abc")).get()).isTrue();
    assertThat(StringValueBuilder.build("abc").eq(StringValueBuilder.build("abcd")).get()).isFalse();
    assertThat(StringValueBuilder.build("").eq(StringValueBuilder.build("")).get()).isTrue();

    assertThat(StringValueBuilder.build("true").eq(BooleanValueBuilder.build(true)).get()).isFalse();
    assertThat(StringValueBuilder.build("1").eq(IntegerValueBuilder.build(1)).get()).isFalse();
    assertThat(StringValueBuilder.build("1").eq(RealValueBuilder.build(1.0)).get()).isFalse();
    assertThat(StringValueBuilder.build("[1]").eq(ListValueBuilder.build(1)).get()).isFalse();
    assertThat(StringValueBuilder.build("[1:2]").eq(MapValueBuilder.build(1, 2)).get()).isFalse();

    assertThat(StringValueBuilder.build("abc").eq(VoidValues.get()).get()).isFalse();
    assertThat(StringValueBuilder.build("").eq(VoidValues.get()).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(StringValueBuilder.build("abc").isVoid().get()).isFalse();
    assertThat(StringValueBuilder.build("void").isVoid().get()).isFalse();
    assertThat(StringValueBuilder.build("").isVoid().get()).isFalse();
  }

  @Test
  public void testIsEmpty() throws ResolveTemplateException {
    assertThat(StringValueBuilder.build("abc").isEmpty().get()).isFalse();
    assertThat(StringValueBuilder.build("").isEmpty().get()).isTrue();
  }

  @Test
  public void testIsNotEmpty() throws ResolveTemplateException {
    assertThat(StringValueBuilder.build("abc").isNotEmpty().get()).isTrue();
    assertThat(StringValueBuilder.build("").isNotEmpty().get()).isFalse();
  }

  @Test
  public void testIsBlank() throws ResolveTemplateException {
    assertThat(StringValueBuilder.build("abc").isBlank().get()).isFalse();
    assertThat(StringValueBuilder.build("").isBlank().get()).isTrue();
    assertThat(StringValueBuilder.build(" ").isBlank().get()).isTrue();
    assertThat(StringValueBuilder.build("\t\r\n").isBlank().get()).isTrue();
  }

  @Test
  public void testIsNotBlank() throws ResolveTemplateException {
    assertThat(StringValueBuilder.build("abc").isNotBlank().get()).isTrue();
    assertThat(StringValueBuilder.build("").isNotBlank().get()).isFalse();
    assertThat(StringValueBuilder.build(" ").isNotBlank().get()).isFalse();
    assertThat(StringValueBuilder.build("\t\r\n").isNotBlank().get()).isFalse();
  }

  @Test
  public void testCapitalizeFirstLetter() throws ResolveTemplateException {
    assertThat(StringValueBuilder.build("abc").capitalizeFirstLetter().get()).isEqualTo("Abc");
    assertThat(StringValueBuilder.build("").capitalizeFirstLetter().get()).isEqualTo("");
    assertThat(StringValueBuilder.build(" \t\r\n").capitalizeFirstLetter().get()).isEqualTo(" \t\r\n");
  }

  @Test
  public void testInvert() {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").invert())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'invert' is not applicable for value type string. Expected boolean, integer or real");
  }

  @Test
  public void testGet() throws Exception {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").get(BooleanValueBuilder.build(true)))
        .isExactlyInstanceOf(IrregularValueTypeException.class)
        .hasMessage("Invalid index type: boolean. Expected integer value");

    Value stringValue = StringValueBuilder.build("abc");

    Value element0 = stringValue.get(IntegerValueBuilder.build(0));
    assertThat(element0.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element0)).isEqualTo("a");
    assertThat(element0.index().asInteger().get()).isEqualTo(0);
    assertThat(element0.isFirst().asBoolean().get()).isTrue();
    assertThat(element0.isLast().asBoolean().get()).isFalse();

    Value element2 = stringValue.get(IntegerValueBuilder.build(2));
    assertThat(element2.type()).isEqualTo(ValueTypes.String);
    assertThat(ValueFunctions.valueToObject(element2)).isEqualTo("c");
    assertThat(element2.index().asInteger().get()).isEqualTo(2);
    assertThat(element2.isFirst().asBoolean().get()).isFalse();
    assertThat(element2.isLast().asBoolean().get()).isTrue();

    Value negativeElement = stringValue.get(IntegerValueBuilder.build(-1));
    assertThat(negativeElement.type()).isEqualTo(ValueTypes.Void);
    assertThat(negativeElement.index().asInteger().get()).isEqualTo(-1);
    assertThatThrownBy(negativeElement::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(negativeElement::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value element3 = stringValue.get(IntegerValueBuilder.build(3));
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
    StringValue string = StringValueBuilder.build("abc");

    Value substringA = string.find(StringValueBuilder.build("a"));
    assertThat(substringA.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringA)).isEqualTo("a");
    assertThat(substringA.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(substringA::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringA::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringC = string.find(StringValueBuilder.build("c"));
    assertThat(substringC.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringC)).isEqualTo("c");
    assertThat(substringC.index().asInteger().get()).isEqualTo(2);
    assertThatThrownBy(substringC::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringC::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringAbc = string.find(StringValueBuilder.build("abc"));
    assertThat(substringAbc.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substringAbc)).isEqualTo("abc");
    assertThat(substringAbc.index().asInteger().get()).isEqualTo(0);
    assertThatThrownBy(substringAbc::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substringAbc::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substringD = string.find(StringValueBuilder.build("d"));
    assertThat(substringD.isVoid().get()).isTrue();

    Value emptySubstring = string.find(StringValueBuilder.build(""));
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
    StringValue string = StringValueBuilder.build("a1c");

    Value substring1 = string.find(IntegerValueBuilder.build(1));
    assertThat(substring1.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring1)).isEqualTo("1");
    assertThat(substring1.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring1::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring1::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring2 = string.find(IntegerValueBuilder.build(2));
    assertThat(substring2.isVoid().get()).isTrue();
  }

  @Test
  public void testFind_whenLettersAndReal() throws Exception {
    StringValue string1 = StringValueBuilder.build("a3.14c");

    Value substring3p14 = string1.find(RealValueBuilder.build(3.14));
    assertThat(substring3p14.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring3p14)).isEqualTo("3.14");
    assertThat(substring3p14.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring3p14::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring3p14::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring3 = string1.find(IntegerValueBuilder.build(3));
    assertThat(substring3.isVoid().get()).isFalse();
    assertThat(ValueFunctions.valueToObject(substring3)).isEqualTo("3");
    assertThat(substring3.index().asInteger().get()).isEqualTo(1);
    assertThatThrownBy(substring3::isFirst)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
    assertThatThrownBy(substring3::isLast)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");

    Value substring3p0 = string1.find(RealValueBuilder.build(3));
    assertThat(substring3p0.isVoid().get()).isTrue();

    Value substring3p16 = string1.find(RealValueBuilder.build(3.16));
    assertThat(substring3p16.isVoid().get()).isTrue();

    StringValue string2 = StringValueBuilder.build("a3.0c");
    substring3p0 = string2.find(RealValueBuilder.build(3));
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
    assertThatThrownBy(() -> StringValueBuilder.build("abc").index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> StringValueBuilder.build("abc").isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
