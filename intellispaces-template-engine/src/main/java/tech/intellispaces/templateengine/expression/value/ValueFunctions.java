package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.general.exception.UnexpectedExceptions;
import tech.intellispaces.general.function.FunctionFunctions;
import tech.intellispaces.general.function.Functions;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.exception.ResolveTemplateExceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ValueFunctions {

  /**
   * Strict casting of Java object to value.
   *
   * @param object the Java object.
   * @return the value.
   * @throws ResolveTemplateException throws if Java object can't be cast to value.
   */
  static Value objectToValue(Object object) throws ResolveTemplateException {
    if (object == null) {
      return VoidValues.get();
    } else if (object instanceof Boolean) {
      return BooleanValues.of((boolean) object);
    } else if (object instanceof Integer) {
      return IntegerValues.of((int) object);
    } else if (object instanceof Double) {
      return RealValues.of((double) object);
    } else if (object instanceof Character) {
      return StringValues.of((Character) object);
    } else if (object instanceof String) {
      return StringValues.of((String) object);
    } else if (object instanceof List) {
      return listToValue((List<?>) object);
    } else if (object instanceof Map) {
      return mapToValue((Map<?, ?>) object);
    }
    throw ResolveTemplateExceptions.withMessage("Object of type {0} cannot be casted to value",
        object.getClass().getCanonicalName());
  }

  private static ListValue listToValue(List<?> list) throws ResolveTemplateException {
    List<Value> values = FunctionFunctions.applyAndUnwrap(
        list.stream(),
        (stream) -> stream
          .map(Functions.wrapThrowingFunction(ValueFunctions::objectToValue))
          .toList(),
        ResolveTemplateException.class);
    return ListValueBuilder.build(values);
  }

  private static MapValue mapToValue(Map<?, ?> map) throws ResolveTemplateException {
    Map<Value, Value> values = new HashMap<>();
    FunctionFunctions.runAndUnwrap(
        () -> map.entrySet().stream()
          .map(Functions.wrapThrowingFunction(ValueFunctions::entryOfObjectsToValues))
          .forEach(e -> values.put(e.getKey(), e.getValue())),
        ResolveTemplateException.class);
    return MapValues.of(values);
  }

  private static Map.Entry<Value, Value> entryOfObjectsToValues(
      Map.Entry<?, ?> e
  ) throws ResolveTemplateException {
    return Map.entry(ValueFunctions.objectToValue(e.getKey()), ValueFunctions.objectToValue(e.getValue()));
  }

  /**
   * Strict casting of the value to java object.
   *
   * @param value the value.
   * @return result java object.
   */
  static Object valueToObject(Value value) {
    value = value.origin();
    if (ValueTypes.Void == value.type()) {
      return null;
    } else if (ValueTypes.Boolean == value.type()) {
      return ((BooleanValue) value).get();
    } else if (ValueTypes.Integer == value.type()) {
      return ((IntegerValue) value).get();
    } else if (ValueTypes.Real == value.type()) {
      return ((RealValue) value).get();
    } else if (ValueTypes.String == value.type()) {
      return ((StringValue) value).get();
    } else if (ValueTypes.List == value.type()) {
      List<Value> values = ((ListValue) value).get();
      return values.stream()
          .map(ValueFunctions::valueToObject)
          .toList();
    } else if (ValueTypes.Map == value.type()) {
      Map<Value, Value> values = ((MapValue) value).get();
      Map<Object, Object> result = new HashMap<>();
      values.forEach((k, v) -> result.put(ValueFunctions.valueToObject(k), ValueFunctions.valueToObject(v)));
      return result;
    }
    throw UnexpectedExceptions.withMessage("Unsupported value type: {0}", value.typename().get());
  }

  /**
   * Soft casting of the value to boolean primitive.
   *
   * @param value the value.
   * @return <code>boolean</code>
   * @throws ResolveTemplateException throws if value can't be cast to boolean primitive.
   */
  static boolean castToBoolean(Value value) throws ResolveTemplateException {
    if (value.type() == ValueTypes.Boolean) {
      return ((BooleanValue) value).get();
    } else if (value.type() == ValueTypes.String) {
      String stringValue = ((StringValue) value).get();
      if (Boolean.TRUE.toString().equals(stringValue)) {
        return true;
      } else if (Boolean.FALSE.toString().equals(stringValue)) {
        return false;
      } else {
        throw ResolveTemplateExceptions.withMessage("String value {0} cannot be casted to boolean primitive",
            stringValue);
      }
    } else if (value.type() == ValueTypes.Integer) {
      int integerValue = ((IntegerValue) value).get();
      if (integerValue == 0) {
        return false;
      } else if (integerValue == 1) {
        return true;
      } else {
        throw ResolveTemplateExceptions.withMessage("Integer value {0} cannot be casted to boolean primitive",
            integerValue);
      }
    } else if (value.type() == ValueTypes.Real) {
      double doubleValue = ((RealValue) value).get();
      if (doubleValue == 0.0) {
        return false;
      } else if (doubleValue == 1.0) {
        return true;
      } else {
        throw ResolveTemplateExceptions.withMessage("Real value {0} cannot be casted to boolean primitive",
            doubleValue);
      }
    }
    throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to boolean primitive",
        value.type().typename());
  }

  /**
   * Soft casting of the value to integer primitive.
   *
   * @param value the value.
   * @return <code>int</code>
   * @throws ResolveTemplateException throws if value can't be cast to integer primitive.
   */
  static int castToInteger(Value value) throws ResolveTemplateException {
    if (value.type() == ValueTypes.Integer) {
      return ((IntegerValue) value).get();
    } else if (value.type() == ValueTypes.String) {
      try {
        return Integer.parseInt(((StringValue) value).get());
      } catch (NumberFormatException e) {
        throw ResolveTemplateExceptions.withMessage("String {0} cannot be casted to integer",
            ((StringValue) value).get());
      }
    } else if (value.type() == ValueTypes.Real) {
      double doubleValue = ((RealValue) value).get();
      int intValue = (int) doubleValue;
      if (doubleValue != intValue) {
        throw ResolveTemplateExceptions.withMessage("Real {0} cannot be casted to integer",
            ((RealValue) value).get());
      }
      return intValue;
    } else if (value.type() == ValueTypes.Boolean) {
      return ((BooleanValue) value).get() ? 1 : 0;
    }
    throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to integer",
        value.type().typename());
  }

  /**
   * Soft casting of the value to double primitive.
   *
   * @param value the value.
   * @return <code>double</code>
   * @throws ResolveTemplateException throws if value can't be cast to double primitive.
   */
  static double castToReal(Value value) throws ResolveTemplateException {
    if (value.type() == ValueTypes.Real) {
      return ((RealValue) value).get();
    } else if (value.type() == ValueTypes.Integer) {
      return ((IntegerValue) value).get();
    } else if (value.type() == ValueTypes.String) {
      try {
        return Double.parseDouble(((StringValue) value).get());
      } catch (NumberFormatException e) {
        throw ResolveTemplateExceptions.withMessage("String {0} cannot be casted to real",
            ((StringValue) value).get());
      }
    } else if (value.type() == ValueTypes.Boolean) {
      return ((BooleanValue) value).get() ? 1.0 : 0.0;
    }
    throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to real",
        value.type().typename());
  }

  /**
   * Soft casting of the value to string object.
   *
   * @param value the value.
   * @return {@link String}
   * @throws ResolveTemplateException throws if value can't be cast to string object.
   */
  static String castToString(Value value) throws ResolveTemplateException {
    if (value.type() == ValueTypes.String) {
      String string = ((StringValue) value).get();
      return (string != null ? string : "");
    } else if (value.type() == ValueTypes.Boolean) {
      return String.valueOf(((BooleanValue) value).get());
    } else if (value.type() == ValueTypes.Integer) {
      return Integer.toString(((IntegerValue) value).get());
    } else if (value.type() == ValueTypes.Real) {
      return Double.toString(((RealValue) value).get());
    } else {
      throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to string",
          value.type().typename());
    }
  }

  /**
   * Soft casting of the value to Java list object.
   *
   * @param value the value.
   * @return {@link List}
   * @throws ResolveTemplateException throws if value can't be cast to list object.
   */
  @SuppressWarnings("unchecked")
  static List<Value> castToList(Value value) throws ResolveTemplateException {
    final List<?> list;
    if (value.type() == ValueTypes.Boolean) {
      list = List.of(((BooleanValue) value));
    } else if (value.type() == ValueTypes.Integer) {
      list = List.of(((IntegerValue) value));
    } else if (value.type() == ValueTypes.Real) {
      list = List.of(((RealValue) value));
    } else if (value.type() == ValueTypes.String) {
      list = List.of(((StringValue) value));
    } else if (value.type() == ValueTypes.List) {
      list = ((ListValue) value).get();
    } else if (value.type() == ValueTypes.Map) {
      list = List.of(((MapValue) value));
    } else {
      throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to list",
          value.type().typename());
    }
    return (List<Value>) list;
  }

  /**
   * Soft casting of the value to Java map object.
   *
   * @param value the value.
   * @return {@link java.util.Map}
   * @throws ResolveTemplateException throws if value can't be cast to map object.
   */
  static Map<Value, Value> castToMap(Value value) throws ResolveTemplateException {
    if (value.type() == ValueTypes.Map) {
      return ((MapValue) value).get();
    } else {
      throw ResolveTemplateExceptions.withMessage("Value of type {0} cannot be casted to map",
          value.type().typename());
    }
  }
}
