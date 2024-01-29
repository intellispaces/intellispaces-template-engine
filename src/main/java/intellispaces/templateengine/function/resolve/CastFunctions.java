package intellispaces.templateengine.function.resolve;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;
import intellispaces.templateengine.builder.value.DoubleValueBuilder;
import intellispaces.templateengine.builder.value.IntegerValueBuilder;
import intellispaces.templateengine.builder.value.ListValueBuilder;
import intellispaces.templateengine.builder.value.MapValueBuilder;
import intellispaces.templateengine.builder.value.StringValueBuilder;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.NullValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CastFunctions {

  static Value castToValue(Object object) throws ResolveTemplateException {
    if (object == null) {
      return NullValue.get();
    } else if (object instanceof Value) {
      return (Value) object;
    } else if (object instanceof Integer) {
      return IntegerValueBuilder.build((int) object);
    } else if (object instanceof Double) {
      return DoubleValueBuilder.build((double) object);
    } else if (object instanceof Boolean) {
      return BooleanValueBuilder.build((boolean) object);
    } else if (object instanceof String) {
      return StringValueBuilder.build((String) object);
    } else if (object instanceof Collection) {
      return ListValueBuilder.build(List.copyOf((List<?>) object));
    } else if (object instanceof Map) {
      return MapValueBuilder.build((Map<?, ?>) object);
    } else {
      throw new ResolveTemplateException("Unsupported object type {}", object.getClass().getCanonicalName());
    }
  }
}
