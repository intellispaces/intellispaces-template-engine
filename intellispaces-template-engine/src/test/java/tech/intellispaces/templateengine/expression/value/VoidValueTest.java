package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.NotApplicableOperationException;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;

/**
 * Tests for {@link VoidValues}.
 */
public class VoidValueTest {

  @Test
  public void testTypename() {
    assertThat(VoidValues.get().typename().get()).isEqualTo(ValueTypes.Void.typename());
  }

  @Test
  public void testAsBoolean() {
    assertThatThrownBy(VoidValues.get()::asBoolean)
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void cannot be casted to boolean primitive");
  }

  @Test
  public void testAsInteger() {
    assertThatThrownBy(VoidValues.get()::asInteger)
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void cannot be casted to integer");
  }

  @Test
  public void testAsReal() {
    assertThatThrownBy(VoidValues.get()::asReal)
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void cannot be casted to real");
  }

  @Test
  public void testAString() throws Exception {
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      String expectedValue = "string";
      castFunctions.when(() -> ValueFunctions.castToString(VoidValues.get())).thenReturn(expectedValue);

      StringValue stringValue = VoidValues.get().asString();
      assertThat(stringValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToString(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws Exception {
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      List<?> expectedValue = List.of();
      castFunctions.when(() -> ValueFunctions.castToList(VoidValues.get())).thenReturn(expectedValue);

      ListValue listValue = VoidValues.get().asList();
      Assertions.assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToList(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    try (MockedStatic<ValueFunctions> castFunctions = Mockito.mockStatic(ValueFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> ValueFunctions.castToMap(VoidValues.get())).thenReturn(expectedValue);

      MapValue mapValue = VoidValues.get().asMap();
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> ValueFunctions.castToMap(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(VoidValues.get().eq(VoidValues.get()).get()).isTrue();

    assertThat(VoidValues.get().eq(BooleanValues.of(false)).get()).isFalse();
    assertThat(VoidValues.get().eq(StringValues.of("0")).get()).isFalse();
    assertThat(VoidValues.get().eq(ListValues.of(0)).get()).isFalse();
    assertThat(VoidValues.get().eq(MapValues.of(0, 0)).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(VoidValues.get().isVoid().get()).isTrue();
  }

  @Test
  public void testIsEmpty() throws ResolveTemplateException {
    assertThat(VoidValues.get().isEmpty().get()).isTrue();
  }

  @Test
  public void testIsNotEmpty() throws ResolveTemplateException {
    assertThat(VoidValues.get().isNotEmpty().get()).isFalse();
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(VoidValues.get()::isBlank)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type void. Expected string");
  }

  @Test
  public void testIsNotBlank() {
    assertThatThrownBy(VoidValues.get()::isNotBlank)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotBlank' is not applicable for value type void. Expected string");
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThatThrownBy(VoidValues.get()::capitalizeFirstLetter)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'capitalizeFirstLetter' is not applicable for value type void. Expected string");
  }

  @Test
  public void testInvert() {
    assertThatThrownBy(VoidValues.get()::invert)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'invert' is not applicable for value type void. Expected boolean, integer or real");
  }

  @Test
  public void testGet() {
    assertThatThrownBy(() -> VoidValues.get().get(IntegerValues.of(0)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'get' is not applicable for value type void. Expected map, list or string");
  }

  @Test
  public void testFind() {
    assertThatThrownBy(() -> VoidValues.get().find(IntegerValues.of(1)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'find' is not applicable for value type void. Expected string or list");
  }

  @Test
  public void testIndex() {
    assertThatThrownBy(() -> VoidValues.get().index())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'index' is not applicable for this value");
  }

  @Test
  public void testIsFirst() {
    assertThatThrownBy(() -> VoidValues.get().isFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Test
  public void testIsNotFirst() {
    assertThatThrownBy(() -> VoidValues.get().isNotFirst())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Test
  public void testIsLast() {
    assertThatThrownBy(() -> VoidValues.get().isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }

  @Test
  public void testIsNotLast() {
    assertThatThrownBy(() -> VoidValues.get().isNotLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isNotLast' is not applicable for this value");
  }
}
