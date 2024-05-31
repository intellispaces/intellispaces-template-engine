package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

import java.util.Map;
import java.util.Objects;

public final class MapValueBuilder {
  private Map<Value, Value> map;

  public static MapValue build(Map<Value, Value> map) {
    Objects.requireNonNull(map);
    return new MapValueImpl(map);
  }

  public static MapValue build(Value key, Value target) {
    return build(Map.of(key, target));
  }

  public static MapValue build(Object key, Object target) throws ResolveTemplateException {
    return build(Map.of(ValueFunctions.objectToValue(key), ValueFunctions.objectToValue(target)));
  }

  public static MapValue build(Object key1, Object target1, Object key2, Object target2) throws ResolveTemplateException {
    return build(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2)));
  }

  public static MapValue build(Object key1, Object target1, Object key2, Object target2, Object key3, Object target3) throws ResolveTemplateException {
    return build(Map.of(
        ValueFunctions.objectToValue(key1), ValueFunctions.objectToValue(target1),
        ValueFunctions.objectToValue(key2), ValueFunctions.objectToValue(target2),
        ValueFunctions.objectToValue(key3), ValueFunctions.objectToValue(target3)));
  }

  public static MapValue empty() {
    return build(Map.of());
  }

  public static MapValueBuilder get() {
    return new MapValueBuilder();
  }

  public MapValueBuilder value(Map<Value, Value> map) {
    this.map = map;
    return this;
  }

  public MapValue build() {
    validate();
    return new MapValueImpl(map);
  }

  private void validate() {
    Objects.requireNonNull(map);
  }

  private MapValueBuilder() {}
}
