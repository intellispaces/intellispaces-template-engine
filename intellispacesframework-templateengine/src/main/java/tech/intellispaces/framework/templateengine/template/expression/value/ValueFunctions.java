package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tech.intellispaces.framework.commons.exception.ExceptionFunctions.coverThrowableFunction;
import static tech.intellispaces.framework.commons.exception.ExceptionFunctions.uncoverThrowable;

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
      return BooleanValueBuilder.build((boolean) object);
    } else if (object instanceof Integer) {
      return IntegerValueBuilder.build((int) object);
    } else if (object instanceof Double) {
      return RealValueBuilder.build((double) object);
    } else if (object instanceof Character) {
      return StringValueBuilder.build((Character) object);
    } else if (object instanceof String) {
      return StringValueBuilder.build((String) object);
    } else if (object instanceof List) {
      return listToValue((List<?>) object);
    } else if (object instanceof Map) {
      return mapToValue((Map<?, ?>) object);
    }
    throw ResolveTemplateException.withMessage("Object of type {} can't be casted to value", object.getClass().getCanonicalName());
  }

  private static ListValue listToValue(List<?> list) throws ResolveTemplateException {
    List<Value> values = uncoverThrowable(ResolveTemplateException.class, list, (l) -> l.stream()
        .map(coverThrowableFunction(ValueFunctions::objectToValue))
        .toList());
    return ListValueBuilder.build(values);
  }

  private static MapValue mapToValue(Map<?, ?> map) throws ResolveTemplateException {
    Map<Value, Value> values = new HashMap<>();
    uncoverThrowable(ResolveTemplateException.class, () -> map.entrySet().stream()
        .map(coverThrowableFunction(e -> Map.entry(ValueFunctions.objectToValue(e.getKey()), ValueFunctions.objectToValue(e.getValue()))))
        .forEach(e -> values.put(e.getKey(), e.getValue())));
    return MapValueBuilder.build(values);
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
    throw UnexpectedViolationException.withMessage("Unsupported value type: {}", value.typename().get());
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
        throw ResolveTemplateException.withMessage("String value {} can't be casted to boolean primitive", stringValue);
      }
    } else if (value.type() == ValueTypes.Integer) {
      int integerValue = ((IntegerValue) value).get();
      if (integerValue == 0) {
        return false;
      } else if (integerValue == 1) {
        return true;
      } else {
        throw ResolveTemplateException.withMessage("Integer value {} can't be casted to boolean primitive", integerValue);
      }
    } else if (value.type() == ValueTypes.Real) {
      double doubleValue = ((RealValue) value).get();
      if (doubleValue == 0.0) {
        return false;
      } else if (doubleValue == 1.0) {
        return true;
      } else {
        throw ResolveTemplateException.withMessage("Real value {} can't be casted to boolean primitive", doubleValue);
      }
    }
    throw ResolveTemplateException.withMessage("Value of type {} can't be casted to boolean primitive", value.type().typename());
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
        throw ResolveTemplateException.withMessage("String {} can't be casted to integer", ((StringValue) value).get());
      }
    } else if (value.type() == ValueTypes.Real) {
      double doubleValue = ((RealValue) value).get();
      int intValue = (int) doubleValue;
      if (doubleValue != intValue) {
        throw ResolveTemplateException.withMessage("Real {} can't be casted to integer", ((RealValue) value).get());
      }
      return intValue;
    } else if (value.type() == ValueTypes.Boolean) {
      return ((BooleanValue) value).get() ? 1 : 0;
    }
    throw ResolveTemplateException.withMessage("Value of type {} can't be casted to integer", value.type().typename());
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
        throw ResolveTemplateException.withMessage("String {} can't be casted to real", ((StringValue) value).get());
      }
    } else if (value.type() == ValueTypes.Boolean) {
      return ((BooleanValue) value).get() ? 1.0 : 0.0;
    }
    throw ResolveTemplateException.withMessage("Value of type {} can't be casted to real", value.type().typename());
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
      throw ResolveTemplateException.withMessage("Value of type {} can't be casted to string", value.type().typename());
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
      throw ResolveTemplateException.withMessage("Value of type {} can't be casted to list", value.type().typename());
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
      throw ResolveTemplateException.withMessage("Value of type {} can't be casted to map", value.type().typename());
    }
  }
}
