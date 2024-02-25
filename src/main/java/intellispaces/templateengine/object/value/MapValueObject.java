package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;
import java.util.Objects;

final class MapValueObject extends AbstractValue implements MapValue {
  private final Map<Value, Value> map;

  MapValueObject(Map<Value, Value> map) {
    this.map = map;
  }

  @Override
  public Map<Value, Value> get() {
    return map;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public MapValue asMap() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.Map) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((MapValue) other).get()));
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get().isEmpty());
  }

  @Override
  public Value fetch(Value key) throws ResolveTemplateException {
    Value value = get().get(key);
    if (value == null) {
      return VoidValues.get();
    }
    return get().get(key);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!MapValue.class.isInstance(o)) {
      return false;
    }
    MapValue other = (MapValue) o;
    return Objects.equals(get(), other.get());
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
