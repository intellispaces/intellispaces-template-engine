package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.NotApplicableOperationException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.cast.CastFunctions;
import intellispaces.templateengine.model.value.ListValue;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.StringValue;
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
        .hasMessage("Value of type void can't be casted to boolean primitive");
  }

  @Test
  public void testAsInteger() {
    assertThatThrownBy(VoidValues.get()::asInteger)
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to integer");
  }

  @Test
  public void testAsReal() {
    assertThatThrownBy(VoidValues.get()::asReal)
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to real");
  }

  @Test
  public void testAString() throws Exception {
    try (MockedStatic<CastFunctions> castFunctions = Mockito.mockStatic(CastFunctions.class)) {
      var expectedValue = "string";
      castFunctions.when(() -> CastFunctions.castToString(VoidValues.get())).thenReturn(expectedValue);

      StringValue stringValue = VoidValues.get().asString();
      assertThat(stringValue.get()).isEqualTo(expectedValue);
      castFunctions.verify(() -> CastFunctions.castToString(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testAsList() throws Exception {
    try (MockedStatic<CastFunctions> castFunctions = Mockito.mockStatic(CastFunctions.class)) {
      var expectedValue = List.of();
      castFunctions.when(() -> CastFunctions.castToList(VoidValues.get())).thenReturn(expectedValue);

      ListValue listValue = VoidValues.get().asList();
      assertThat(listValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> CastFunctions.castToList(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testAsMap() throws Exception {
    try (MockedStatic<CastFunctions> castFunctions = Mockito.mockStatic(CastFunctions.class)) {
      var expectedValue = new LinkedHashMap<>();
      castFunctions.when(() -> CastFunctions.castToMap(VoidValues.get())).thenReturn(expectedValue);

      MapValue mapValue = VoidValues.get().asMap();
      assertThat(mapValue.get()).isSameAs(expectedValue);
      castFunctions.verify(() -> CastFunctions.castToMap(same(VoidValues.get())), Mockito.times(1));
    }
  }

  @Test
  public void testEq() throws ResolveTemplateException {
    assertThat(VoidValues.get().eq(VoidValues.get()).get()).isTrue();

    assertThat(VoidValues.get().eq(BooleanValueBuilder.build(false)).get()).isFalse();
    assertThat(VoidValues.get().eq(StringValueBuilder.build("0")).get()).isFalse();
    assertThat(VoidValues.get().eq(ListValueBuilder.build(0)).get()).isFalse();
    assertThat(VoidValues.get().eq(MapValueBuilder.build(0, 0)).get()).isFalse();
  }

  @Test
  public void testIsVoid() {
    assertThat(VoidValues.get().isVoid().get()).isTrue();
  }

  @Test
  public void testIsEmpty() {
    assertThatThrownBy(VoidValues.get()::isEmpty)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isEmpty' is not applicable for value type void. Expected string, List or map");
  }

  @Test
  public void testIsBlank() {
    assertThatThrownBy(VoidValues.get()::isBlank)
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isBlank' is not applicable for value type void. Expected string");
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
  public void testFetch() {
    assertThatThrownBy(() -> VoidValues.get().fetch(IntegerValueBuilder.build(0)))
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'fetch' is not applicable for value type void. Expected map, list or string");
  }

  @Test
  public void testFind() {
    assertThatThrownBy(() -> VoidValues.get().find(IntegerValueBuilder.build(1)))
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
  public void testIsLast() {
    assertThatThrownBy(() -> VoidValues.get().isLast())
        .isExactlyInstanceOf(NotApplicableOperationException.class)
        .hasMessage("Operation 'isLast' is not applicable for this value");
  }
}
