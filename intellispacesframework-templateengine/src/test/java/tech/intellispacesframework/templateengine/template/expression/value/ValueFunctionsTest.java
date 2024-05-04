package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ValueFunctions}.
 */
public class ValueFunctionsTest {

  @Test
  public void testObjectToValue_whenNull() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(null);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Void);
  }

  @Test
  public void testObjectToValue_whenBoolean() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(true);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Boolean);
    assertThat(((BooleanValue) value).get()).isTrue();
  }

  @Test
  public void testObjectToValuewhenInteger() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(1);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Integer);
    assertThat(((IntegerValue) value).get()).isEqualTo(1);
  }

  @Test
  public void testObjectToValue_whenLong() {
    assertThatThrownBy(() -> ValueFunctions.objectToValue(1L))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Long can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenDouble() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(1.2);

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Real);
    assertThat(((RealValue) value).get()).isEqualTo(1.2);
  }

  @Test
  public void testObjectToValue_whenFloat() {
    assertThatThrownBy(() -> ValueFunctions.objectToValue(1.0f))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenString() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue("abc");

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.String);
    assertThat(((StringValue) value).get()).isEqualTo("abc");
  }

  @Test
  public void testObjectToValue_whenChar() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue('i');

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.String);
    assertThat(((StringValue) value).get()).isEqualTo("i");
  }

  @Test
  public void testObjectToValue_whenStringList() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(List.of("a", "b", "c"));

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.List);
    assertThat(((ListValue) value).get()).isEqualTo(List.of(
        StringValueBuilder.build("a"), StringValueBuilder.build("b"), StringValueBuilder.build("c")
    ));
  }

  @Test
  public void testObjectToValue_whenFloatList() {
    assertThatThrownBy(() -> ValueFunctions.objectToValue(List.of(1.0f, 2.0f)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testObjectToValue_whenIntegerToStringMap() throws Exception {
    // When
    Value value = ValueFunctions.objectToValue(Map.of(1, "a", 2, "b"));

    // Then
    assertThat(value.type()).isEqualTo(ValueTypes.Map);
    assertThat(((MapValue) value).get()).isEqualTo(Map.of(
        IntegerValueBuilder.build(1), StringValueBuilder.build("a"),
        IntegerValueBuilder.build(2), StringValueBuilder.build("b")
    ));
  }

  @Test
  public void testObjectToValue_whenIntegerToFloatMap() {
    assertThatThrownBy(() -> ValueFunctions.objectToValue(Map.of(1, 1.0f, 2, 2.0f)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Object of type java.lang.Float can't be casted to value");
  }

  @Test
  public void testValueToObject_whenVoid() {
    assertThat(ValueFunctions.valueToObject(VoidValues.get())).isNull();
  }

  @Test
  public void testValueToObject_whenBoolean() {
    assertThat(ValueFunctions.valueToObject(BooleanValueBuilder.build(true))).isEqualTo(true);
    assertThat(ValueFunctions.valueToObject(BooleanValueBuilder.build(false))).isEqualTo(false);
  }

  @Test
  public void testValueToObject_whenInteger() {
    assertThat(ValueFunctions.valueToObject(IntegerValueBuilder.build(1))).isEqualTo(1);
    assertThat(ValueFunctions.valueToObject(IntegerValueBuilder.build(0))).isEqualTo(0);
    assertThat(ValueFunctions.valueToObject(IntegerValueBuilder.build(-1))).isEqualTo(-1);
  }

  @Test
  public void testValueToObject_whenReal() {
    assertThat(ValueFunctions.valueToObject(RealValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(ValueFunctions.valueToObject(RealValueBuilder.build(0))).isEqualTo(0.0);
    assertThat(ValueFunctions.valueToObject(RealValueBuilder.build(-1))).isEqualTo(-1.0);
  }

  @Test
  public void testValueToObject_whenString() {
    assertThat(ValueFunctions.valueToObject(StringValueBuilder.build(""))).isEqualTo("");
    assertThat(ValueFunctions.valueToObject(StringValueBuilder.build("abc"))).isEqualTo("abc");
    assertThat(ValueFunctions.valueToObject(StringValueBuilder.build('a'))).isEqualTo("a");
  }

  @Test
  public void testValueToObject_whenList() {
    assertThat(ValueFunctions.valueToObject(ListValueBuilder.empty())).isEqualTo(List.of());
    assertThat(ValueFunctions.valueToObject(ListValueBuilder.build("a", "b", "c"))).isEqualTo(List.of("a", "b", "c"));
  }

  @Test
  public void testValueToObject_whenMap() throws Exception {
    assertThat(ValueFunctions.valueToObject(MapValueBuilder.empty())).isEqualTo(Map.of());
    assertThat(ValueFunctions.valueToObject(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(Map.of(1, "a", 2, "b"));
  }

  @Test
  public void testCastToBoolean() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to boolean primitive");

    // When boolean
    assertThat(ValueFunctions.castToBoolean(BooleanValueBuilder.build(true))).isTrue();
    assertThat(ValueFunctions.castToBoolean(BooleanValueBuilder.build(false))).isFalse();

    // When integer
    assertThat(ValueFunctions.castToBoolean(IntegerValueBuilder.build(0))).isFalse();
    assertThat(ValueFunctions.castToBoolean(IntegerValueBuilder.build(1))).isTrue();
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(IntegerValueBuilder.build(2)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Integer value 2 can't be casted to boolean primitive");

    // When real
    assertThat(ValueFunctions.castToBoolean(RealValueBuilder.build(0.0))).isFalse();
    assertThat(ValueFunctions.castToBoolean(RealValueBuilder.build(1.0))).isTrue();
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(RealValueBuilder.build(1.1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Real value 1.1 can't be casted to boolean primitive");

    // When string
    assertThat(ValueFunctions.castToBoolean(StringValueBuilder.build("true"))).isTrue();
    assertThat(ValueFunctions.castToBoolean(StringValueBuilder.build("false"))).isFalse();
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(StringValueBuilder.build("abc")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String value abc can't be casted to boolean primitive");

    // When list
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to boolean primitive");

    // When map
    assertThatThrownBy(() -> ValueFunctions.castToBoolean(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to boolean primitive");
  }

  @Test
  public void testCastToInteger() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToInteger(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to integer");

    // When boolean
    assertThat(ValueFunctions.castToInteger(BooleanValueBuilder.build(true))).isEqualTo(1);
    assertThat(ValueFunctions.castToInteger(BooleanValueBuilder.build(false))).isEqualTo(0);

    // When integer
    assertThat(ValueFunctions.castToInteger(IntegerValueBuilder.build(1))).isEqualTo(1);
    assertThat(ValueFunctions.castToInteger(IntegerValueBuilder.build(-1))).isEqualTo(-1);

    // When real
    assertThat(ValueFunctions.castToInteger(RealValueBuilder.build(1.0))).isEqualTo(1);
    assertThat(ValueFunctions.castToInteger(RealValueBuilder.build(2.0))).isEqualTo(2);
    assertThat(ValueFunctions.castToInteger(RealValueBuilder.build(-2.0))).isEqualTo(-2);
    assertThatThrownBy(() -> ValueFunctions.castToInteger(RealValueBuilder.build(1.1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Real 1.1 can't be casted to integer");

    // When string
    assertThat(ValueFunctions.castToInteger(StringValueBuilder.build("123"))).isEqualTo(123);
    assertThatThrownBy(() -> ValueFunctions.castToInteger(StringValueBuilder.build("1a")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String 1a can't be casted to integer");

    // When list
    assertThatThrownBy(() -> ValueFunctions.castToInteger(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to integer");

    // When map
    assertThatThrownBy(() -> ValueFunctions.castToInteger(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to integer");
  }

  @Test
  public void testCastToReal() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToReal(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to real");

    // When boolean
    assertThat(ValueFunctions.castToReal(BooleanValueBuilder.build(true))).isEqualTo(1.0);
    assertThat(ValueFunctions.castToReal(BooleanValueBuilder.build(false))).isEqualTo(0.0);

    // When integer
    assertThat(ValueFunctions.castToReal(IntegerValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(ValueFunctions.castToReal(IntegerValueBuilder.build(-1))).isEqualTo(-1.0);

    // When real
    assertThat(ValueFunctions.castToReal(RealValueBuilder.build(1))).isEqualTo(1.0);
    assertThat(ValueFunctions.castToReal(RealValueBuilder.build(-1))).isEqualTo(-1.0);

    // When string
    assertThat(ValueFunctions.castToReal(StringValueBuilder.build("1"))).isEqualTo(1.0);
    assertThat(ValueFunctions.castToReal(StringValueBuilder.build("2.0"))).isEqualTo(2.0);
    assertThat(ValueFunctions.castToReal(StringValueBuilder.build("3.14"))).isEqualTo(3.14);
    assertThatThrownBy(() -> ValueFunctions.castToReal(StringValueBuilder.build("3p14")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("String 3p14 can't be casted to real");

    // When list
    assertThatThrownBy(() -> ValueFunctions.castToReal(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to real");

    // When map
    assertThatThrownBy(() -> ValueFunctions.castToReal(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to real");
  }

  @Test
  public void testCastToString() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToString(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to string");

    // When boolean
    assertThat(ValueFunctions.castToString(BooleanValueBuilder.build(true))).isEqualTo("true");
    assertThat(ValueFunctions.castToString(BooleanValueBuilder.build(false))).isEqualTo("false");

    // When integer
    assertThat(ValueFunctions.castToString(IntegerValueBuilder.build(1))).isEqualTo("1");
    assertThat(ValueFunctions.castToString(IntegerValueBuilder.build(-1))).isEqualTo("-1");

    // When real
    assertThat(ValueFunctions.castToString(RealValueBuilder.build(1))).isEqualTo("1.0");
    assertThat(ValueFunctions.castToString(RealValueBuilder.build(-1))).isEqualTo("-1.0");

    // When string
    assertThat(ValueFunctions.castToString(StringValueBuilder.build("abc"))).isEqualTo("abc");

    // When list
    assertThatThrownBy(() -> ValueFunctions.castToString(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to string");

    // When map
    assertThatThrownBy(() -> ValueFunctions.castToString(MapValueBuilder.build(1, "a", 2, "b")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type map can't be casted to string");
  }

  @Test
  public void testCastToList() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToList(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to list");

    // When boolean
    assertThat(ValueFunctions.castToList(BooleanValueBuilder.build(true))).isEqualTo(List.of(BooleanValueBuilder.build(true)));
    assertThat(ValueFunctions.castToList(BooleanValueBuilder.build(false))).isEqualTo(List.of(BooleanValueBuilder.build(false)));

    // When integer
    assertThat(ValueFunctions.castToList(IntegerValueBuilder.build(1))).isEqualTo(List.of(IntegerValueBuilder.build(1)));
    assertThat(ValueFunctions.castToList(IntegerValueBuilder.build(-1))).isEqualTo(List.of(IntegerValueBuilder.build(-1)));

    // When real
    assertThat(ValueFunctions.castToList(RealValueBuilder.build(1))).isEqualTo(List.of(RealValueBuilder.build(1)));
    assertThat(ValueFunctions.castToList(RealValueBuilder.build(-1))).isEqualTo(List.of(RealValueBuilder.build(-1)));

    // When string
    assertThat(ValueFunctions.castToList(StringValueBuilder.build("abc"))).isEqualTo(List.of(StringValueBuilder.build("abc")));

    // When list
    assertThat(ValueFunctions.castToList(ListValueBuilder.build(1, 2))).isEqualTo(List.of(IntegerValueBuilder.build(1), IntegerValueBuilder.build(2)));

    // When map
    assertThat(ValueFunctions.castToList(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(List.of(
        MapValueBuilder.build(1, "a", 2, "b")));
  }

  @Test
  public void testCastToMap() throws Exception {
    // When void
    assertThatThrownBy(() -> ValueFunctions.castToMap(VoidValues.get()))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type void can't be casted to map");

    // When boolean
    assertThatThrownBy(() -> ValueFunctions.castToMap(BooleanValueBuilder.build(true)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type boolean can't be casted to map");

    // When integer
    assertThatThrownBy(() -> ValueFunctions.castToMap(IntegerValueBuilder.build(1)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type integer can't be casted to map");

    // When real
    assertThatThrownBy(() -> ValueFunctions.castToMap(RealValueBuilder.build(1.0)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type real can't be casted to map");

    // When string
    assertThatThrownBy(() -> ValueFunctions.castToMap(StringValueBuilder.build("abc")))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type string can't be casted to map");

    // When list
    assertThatThrownBy(() -> ValueFunctions.castToMap(ListValueBuilder.build(1, 2, 3)))
        .isExactlyInstanceOf(ResolveTemplateException.class)
        .hasMessage("Value of type list can't be casted to map");

    // When map
    assertThat(ValueFunctions.castToMap(MapValueBuilder.build(1, "a", 2, "b"))).isEqualTo(Map.of(
        IntegerValueBuilder.build(1), StringValueBuilder.build("a"),
        IntegerValueBuilder.build(2), StringValueBuilder.build("b")));
  }
}
