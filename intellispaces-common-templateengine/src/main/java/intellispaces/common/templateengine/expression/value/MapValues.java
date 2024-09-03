package intellispaces.common.templateengine.expression.value;

import intellispaces.common.templateengine.exception.ResolveTemplateException;

import java.util.Map;
import java.util.Objects;

public final class MapValues {

  private MapValues() {}

  public static MapValue of(Map<Value, Value> map) {
    Objects.requireNonNull(map);
    return new MapValueImpl(map);
  }

  public static MapValue of(Value key, Value target) {
    return of(Map.of(key, target));
  }

  public static MapValue of(
      Object key, Object target
  ) throws ResolveTemplateException {
    return of(Map.of(ValueFunctions.objectToValue(key), ValueFunctions.objectToValue(target)));
  }

  public static MapValue of(
      Object key1, Object target1,
      Object key2, Object target2
  ) throws ResolveTemplateException {
    return of(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2)));
  }

  public static MapValue of(
      Object key1, Object target1,
      Object key2, Object target2,
      Object key3, Object target3
  ) throws ResolveTemplateException {
    return of(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2),
        ValueFunctions.objectToValue(key3), ValueFunctions.objectToValue(target3)));
  }

  public static MapValue empty() {
    return EMPTY;
  }

  public static MapValueBuilder build() {
    return new MapValueBuilder();
  }

  private static final MapValue EMPTY = of(Map.of());
}
