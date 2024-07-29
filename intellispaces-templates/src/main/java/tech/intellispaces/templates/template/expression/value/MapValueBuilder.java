package tech.intellispaces.templates.template.expression.value;

import java.util.Map;
import java.util.Objects;

public final class MapValueBuilder {
  private Map<Value, Value> map;

  MapValueBuilder value(Map<Value, Value> map) {
    this.map = map;
    return this;
  }

  public MapValue get() {
    validate();
    return new MapValueImpl(map);
  }

  private void validate() {
    Objects.requireNonNull(map);
  }
}
