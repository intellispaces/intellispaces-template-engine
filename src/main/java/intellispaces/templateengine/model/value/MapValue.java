package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.CastFunctions;

import java.util.Map;

public interface MapValue extends Value {

  Map<?, ?> get();

  @Override
  default ValueType type() {
    return ValueTypes.Map;
  }

  @Override
  default BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get().isEmpty());
  }

  @Override
  default Value match(Value keyValue) throws ResolveTemplateException {
    var key = getKey(keyValue);
    return CastFunctions.castToValue(get().get(key));
  }

  @Override
  default BooleanValue contains(Value keyValue) throws ResolveTemplateException {
    var key = getKey(keyValue);
    return BooleanValueBuilder.build(get().containsKey(key));
  }

  default Object getKey(Value keyValue) throws ResolveTemplateException {
    if (ValueTypes.Item.typename().equals(keyValue.typename().get())) {
      return getKey(((ItemValue) keyValue).get());
    } else if (ValueTypes.Null.typename().equals(keyValue.typename().get())) {
      return null;
    } else if (ValueTypes.Boolean.typename().equals(keyValue.typename().get())) {
      return ((BooleanValue) keyValue).get();
    } else if (ValueTypes.Integer.typename().equals(keyValue.typename().get())) {
      return  ((IntegerValue) keyValue).get();
    } else if (ValueTypes.Double.typename().equals(keyValue.typename().get())) {
      return  ((DoubleValue) keyValue).get();
    } else if (ValueTypes.String.typename().equals(keyValue.typename().get())) {
      return  ((StringValue) keyValue).get();
    }
    throw new ResolveTemplateException("Unsupported key value type: {}", keyValue.typename().get());
  }
}
