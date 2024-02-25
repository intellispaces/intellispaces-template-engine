package intellispaces.templateengine.function.cast;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.object.value.BooleanValueBuilder;
import intellispaces.templateengine.object.value.IntegerValueBuilder;
import intellispaces.templateengine.object.value.ListValueBuilder;
import intellispaces.templateengine.object.value.MapValueBuilder;
import intellispaces.templateengine.object.value.RealValueBuilder;
import intellispaces.templateengine.object.value.StringValueBuilder;
import intellispaces.templateengine.object.value.VoidValues;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.ListValue;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.RealValue;
import intellispaces.templateengine.model.value.StringValue;
import intellispaces.templateengine.model.value.Value;
import intellispaces.templateengine.object.value.ValueTypes;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link CastFunctions}.
 */
public class CastFunctionsTest {

  @Test
  public void testObjectToValue_whenNull() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(null);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Void);
  }

  @Test
  public void testObjectToValue_whenBoolean() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(true);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Boolean);
    assertThat(((BooleanValue) value).get()).isTrue();
  }

  @Test
  public void testObjectToValuewhenInteger() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(1);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Integer);
    assertThat(((IntegerValue) value).get()).isEqualTo(1);
  }

  @Test
  public void testObjectToValue_whenLong() {
    assertThatThrownBy(() -> CastFunctions.objectToValue(1L))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Long can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenDouble() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(1.2);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Real);
    assertThat(((RealValue) value).get()).isEqualTo(1.2);
  }

  @Test
  public void testObjectToValue_whenFloat() {
    assertThatThrownBy(() -> CastFunctions.objectToValue(1.0f))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenString() throws Exception {
    // When
    Value value = CastFunctions.objectToValue("abc");

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.String);
    assertThat(((StringValue) value).get()).isEqualTo("abc");
  }

  @Test
  public void testObjectToValue_whenChar() throws Exception {
    // When
    Value value = CastFunctions.objectToValue('i');

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.String);
    assertThat(((StringValue) value).get()).isEqualTo("i");
  }

  @Test
  public void testObjectToValue_whenStringList() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(List.of("a", "b", "c"));

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.List);
    assertThat(((ListValue) value).get()).isEqualTo(List.of(
        StringValueBuilder.build("a"), StringValueBuilder.build("b"), StringValueBuilder.build("c")
    ));
  }

  @Test
  public void testObjectToValue_whenFloatList() {
    assertThatThrownBy(() -> CastFunctions.objectToValue(List.of(1.0f, 2.0f)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenIntegerToStringMap() throws Exception {
    // When
    Value value = CastFunctions.objectToValue(Map.of(1, "a", 2, "b"));

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Map);
    assertThat(((MapValue) value).get()).isEqualTo(Map.of(
        IntegerValueBuilder.build(1), StringValueBuilder.build("a"),
        IntegerValueBuilder.build(2), StringValueBuilder.build("b")
    ));
  }

  @Test
  public void testObjectToValue_whenIntegerToFloatMap() {
    assertThatThrownBy(() -> CastFunctions.objectToValue(Map.of(1, 1.0f, 2, 2.0f)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testValueToObject_whenVoid() {
    assertThat(CastFunctions.valueToObject(VoidValues.get())).isNull();
  }

  @Test
  public void testValueToObject_whenBoolean() {
    assertThat(CastFunctions.valueToObject(BooleanValueBuilder.build(true))).isEqualTo(true);
    assertThat(CastFunctions.valueToObject(BooleanValueBuilder.build(false))).isEqualTo(false);
  }

  @Test
  public void testValueToObject_whenInteger() {
    assertThat(CastFunctions.valueToObject(IntegerValueBuilder.build(1))).isEqualTo(1);
    assertThat(CastFunctions.valueToObject(IntegerValueBuilder.build(0))).isEqualTo(0);
    assertThat(CastFunctions.valueToObject(IntegerValueBuilder.build(-1))).isEqualTo(-1);
  }

  @Test
  public void testValueToObject_whenReal() {
    assertThat(CastFunctions.valueToObject(RealValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(CastFunctions.valueToObject(RealValueBuilder.build(0))).isEqualTo(0.0);
    assertThat(CastFunctions.valueToObject(RealValueBuilder.build(-1))).isEqualTo(-1.0);
  }

  @Test
  public void testValueToObject_whenString() {
    assertThat(CastFunctions.valueToObject(StringValueBuilder.build(""))).isEqualTo("");
    assertThat(CastFunctions.valueToObject(StringValueBuilder.build("abc"))).isEqualTo("abc");
    assertThat(CastFunctions.valueToObject(StringValueBuilder.build('a'))).isEqualTo("a");
  }

  @Test
  public void testValueToObject_whenList() {
    assertThat(CastFunctions.valueToObject(ListValueBuilder.empty())).isEqualTo(List.of());
    assertThat(CastFunctions.valueToObject(ListValueBuilder.build("a", "b", "c"))).isEqualTo(List.of("a", "b", "c"));
  }

  @Test
  public void testValueToObject_whenMap() throws Exception {
    assertThat(CastFunctions.valueToObject(MapValueBuilder.empty())).isEqualTo(Map.of());
    assertThat(CastFunctions.valueToObject(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(Map.of(1, "a", 2, "b"));
  }

  @Test
  public void testCastToBoolean() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToBoolean(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to boolean primitive");

    // When boolean
    assertThat(CastFunctions.castToBoolean(BooleanValueBuilder.build(true))).isTrue();
    assertThat(CastFunctions.castToBoolean(BooleanValueBuilder.build(false))).isFalse();

    // When integer
    assertThat(CastFunctions.castToBoolean(IntegerValueBuilder.build(0))).isFalse();
    assertThat(CastFunctions.castToBoolean(IntegerValueBuilder.build(1))).isTrue();
    assertThatThrownBy(() -> CastFunctions.castToBoolean(IntegerValueBuilder.build(2)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Integer value 2 can't be casted to boolean primitive");

    // When real
    assertThat(CastFunctions.castToBoolean(RealValueBuilder.build(0.0))).isFalse();
    assertThat(CastFunctions.castToBoolean(RealValueBuilder.build(1.0))).isTrue();
    assertThatThrownBy(() -> CastFunctions.castToBoolean(RealValueBuilder.build(1.1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Real value 1.1 can't be casted to boolean primitive");

    // When string
    assertThat(CastFunctions.castToBoolean(StringValueBuilder.build("true"))).isTrue();
    assertThat(CastFunctions.castToBoolean(StringValueBuilder.build("false"))).isFalse();
    assertThatThrownBy(() -> CastFunctions.castToBoolean(StringValueBuilder.build("abc")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String value abc can't be casted to boolean primitive");

    // When list
    assertThatThrownBy(() -> CastFunctions.castToBoolean(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to boolean primitive");

    // When map
    assertThatThrownBy(() -> CastFunctions.castToBoolean(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to boolean primitive");
  }

  @Test
  public void testCastToInteger() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToInteger(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to integer");

    // When boolean
    assertThat(CastFunctions.castToInteger(BooleanValueBuilder.build(true))).isEqualTo(1);
    assertThat(CastFunctions.castToInteger(BooleanValueBuilder.build(false))).isEqualTo(0);

    // When integer
    assertThat(CastFunctions.castToInteger(IntegerValueBuilder.build(1))).isEqualTo(1);
    assertThat(CastFunctions.castToInteger(IntegerValueBuilder.build(-1))).isEqualTo(-1);

    // When real
    assertThat(CastFunctions.castToInteger(RealValueBuilder.build(1.0))).isEqualTo(1);
    assertThat(CastFunctions.castToInteger(RealValueBuilder.build(2.0))).isEqualTo(2);
    assertThat(CastFunctions.castToInteger(RealValueBuilder.build(-2.0))).isEqualTo(-2);
    assertThatThrownBy(() -> CastFunctions.castToInteger(RealValueBuilder.build(1.1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Real 1.1 can't be casted to integer");

    // When string
    assertThat(CastFunctions.castToInteger(StringValueBuilder.build("123"))).isEqualTo(123);
    assertThatThrownBy(() -> CastFunctions.castToInteger(StringValueBuilder.build("1a")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String 1a can't be casted to integer");

    // When list
    assertThatThrownBy(() -> CastFunctions.castToInteger(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to integer");

    // When map
    assertThatThrownBy(() -> CastFunctions.castToInteger(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to integer");
  }

  @Test
  public void testCastToReal() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToReal(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to real");

    // When boolean
    assertThat(CastFunctions.castToReal(BooleanValueBuilder.build(true))).isEqualTo(1.0);
    assertThat(CastFunctions.castToReal(BooleanValueBuilder.build(false))).isEqualTo(0.0);

    // When integer
    assertThat(CastFunctions.castToReal(IntegerValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(CastFunctions.castToReal(IntegerValueBuilder.build(-1))).isEqualTo(-1.0);

    // When real
    assertThat(CastFunctions.castToReal(RealValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(CastFunctions.castToReal(RealValueBuilder.build(-1))).isEqualTo(-1.0);

    // When string
    assertThat(CastFunctions.castToReal(StringValueBuilder.build("1"))).isEqualTo(1.0);
    assertThat(CastFunctions.castToReal(StringValueBuilder.build("2.0"))).isEqualTo(2.0);
    assertThat(CastFunctions.castToReal(StringValueBuilder.build("3.14"))).isEqualTo(3.14);
    assertThatThrownBy(() -> CastFunctions.castToReal(StringValueBuilder.build("3p14")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String 3p14 can't be casted to real");

    // When list
    assertThatThrownBy(() -> CastFunctions.castToReal(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to real");

    // When map
    assertThatThrownBy(() -> CastFunctions.castToReal(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to real");
  }

  @Test
  public void testCastToString() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToString(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to string");

    // When boolean
    assertThat(CastFunctions.castToString(BooleanValueBuilder.build(true))).isEqualTo("true");
    assertThat(CastFunctions.castToString(BooleanValueBuilder.build(false))).isEqualTo("false");

    // When integer
    assertThat(CastFunctions.castToString(IntegerValueBuilder.build(1))).isEqualTo("1");
    assertThat(CastFunctions.castToString(IntegerValueBuilder.build(-1))).isEqualTo("-1");

    // When real
    assertThat(CastFunctions.castToString(RealValueBuilder.build(1))).isEqualTo("1.0");
    assertThat(CastFunctions.castToString(RealValueBuilder.build(-1))).isEqualTo("-1.0");

    // When string
    assertThat(CastFunctions.castToString(StringValueBuilder.build("abc"))).isEqualTo("abc");

    // When list
    assertThatThrownBy(() -> CastFunctions.castToString(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to string");

    // When map
    assertThatThrownBy(() -> CastFunctions.castToString(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to string");
  }

  @Test
  public void testCastToList() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToList(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to list");

    // When boolean
    assertThat(CastFunctions.castToList(BooleanValueBuilder.build(true))).isEqualTo(List.of(BooleanValueBuilder.build(true)));
    assertThat(CastFunctions.castToList(BooleanValueBuilder.build(false))).isEqualTo(List.of(BooleanValueBuilder.build(false)));

    // When integer
    assertThat(CastFunctions.castToList(IntegerValueBuilder.build(1))).isEqualTo(List.of(IntegerValueBuilder.build(1)));
    assertThat(CastFunctions.castToList(IntegerValueBuilder.build(-1))).isEqualTo(List.of(IntegerValueBuilder.build(-1)));

    // When real
    assertThat(CastFunctions.castToList(RealValueBuilder.build(1))).isEqualTo(List.of(RealValueBuilder.build(1)));
    assertThat(CastFunctions.castToList(RealValueBuilder.build(-1))).isEqualTo(List.of(RealValueBuilder.build(-1)));

    // When string
    assertThat(CastFunctions.castToList(StringValueBuilder.build("abc"))).isEqualTo(List.of(StringValueBuilder.build("abc")));

    // When list
    assertThat(CastFunctions.castToList(ListValueBuilder.build(1, 2))).isEqualTo(List.of(IntegerValueBuilder.build(1), IntegerValueBuilder.build(2)));

    // When map
    assertThat(CastFunctions.castToList(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(List.of(
        MapValueBuilder.build(1, "a", 2, "b")));
  }

  @Test
  public void testCastToMap() throws Exception {
    // When void
    assertThatThrownBy(() -> CastFunctions.castToMap(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to map");

    // When boolean
    assertThatThrownBy(() -> CastFunctions.castToMap(BooleanValueBuilder.build(true)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type boolean can't be casted to map");

    // When integer
    assertThatThrownBy(() -> CastFunctions.castToMap(IntegerValueBuilder.build(1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type integer can't be casted to map");

    // When real
    assertThatThrownBy(() -> CastFunctions.castToMap(RealValueBuilder.build(1.0)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type real can't be casted to map");

    // When string
    assertThatThrownBy(() -> CastFunctions.castToMap(StringValueBuilder.build("abc")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type string can't be casted to map");

    // When list
    assertThatThrownBy(() -> CastFunctions.castToMap(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to map");

    // When map
    assertThat(CastFunctions.castToMap(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(Map.of(
        IntegerValueBuilder.build(1), StringValueBuilder.build("a"),
        IntegerValueBuilder.build(2), StringValueBuilder.build("b")));
  }
}
