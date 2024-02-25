package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.cast.CastFunctions;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;
import java.util.Objects;

public final class MapValueBuilder {
  private Map<Value, Value> map;

  public static MapValue build(Map<Value, Value> map) {
    Objects.requireNonNull(map);
    return new MapValueObject(map);
  }

  public static MapValue build(Value key, Value target) {
    return build(Map.of(key, target));
  }

  public static MapValue build(Object key, Object target) throws ResolveTemplateException {
    return build(Map.of(CastFunctions.objectToValue(key), CastFunctions.objectToValue(target)));
  }

  public static MapValue build(Object key1, Object target1, Object key2, Object target2) throws ResolveTemplateException {
    return build(Map.of(
        CastFunctions.objectToValue(key1), CastFunctions.objectToValue(target1),
        CastFunctions.objectToValue(key2), CastFunctions.objectToValue(target2)));
  }

  public static MapValue build(Object key1, Object target1, Object key2, Object target2, Object key3, Object target3) throws ResolveTemplateException {
    return build(Map.of(
        CastFunctions.objectToValue(key1), CastFunctions.objectToValue(target1),
        CastFunctions.objectToValue(key2), CastFunctions.objectToValue(target2),
        CastFunctions.objectToValue(key3), CastFunctions.objectToValue(target3)));
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
    return new MapValueObject(map);
  }

  private void validate() {
    Objects.requireNonNull(map);
  }

  private MapValueBuilder() {}
}
