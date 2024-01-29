package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.MapValue;

import java.util.Map;

final class MapValueImpl implements MapValue {
 private final Map<?, ?> map;

 MapValueImpl(Map<?, ?> map) {
  this.map = map;
 }

 @Override
 public Map<?, ?> get() {
  return map;
 }
}
