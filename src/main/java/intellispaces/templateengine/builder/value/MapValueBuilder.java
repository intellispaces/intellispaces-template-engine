package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.MapValue;

import java.util.Map;

public final class MapValueBuilder {

  public static MapValue build(Map<?, ?> map) {
    return new MapValueImpl(map);
  }
}
