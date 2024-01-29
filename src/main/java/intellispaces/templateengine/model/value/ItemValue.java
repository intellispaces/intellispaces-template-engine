package intellispaces.templateengine.model.value;

import intellispaces.templateengine.exception.ResolveTemplateException;

public interface ItemValue extends Value {

  Value get();

  @Override
  default ValueType type() {
    return ValueTypes.Item;
  }

  @Override
  default BooleanValue eq(Value other) throws ResolveTemplateException {
    return get().eq(other);
  }

  @Override
  default BooleanValue isBlank() throws ResolveTemplateException {
    return get().isBlank();
  }

  @Override
  default StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    return get().capitalizeFirstLetter();
  }

  @Override
  default BooleanValue not() throws ResolveTemplateException {
    return get().not();
  }

  @Override
  default Value at(Value indexValue) throws ResolveTemplateException {
    return get().at(indexValue);
  }

  @Override
  default Value match(Value keyValue) throws ResolveTemplateException {
    return get().match(keyValue);
  }

  @Override
  default Value get(Value keyValue) throws ResolveTemplateException {
    return get().get(keyValue);
  }

  @Override
  default BooleanValue contains(Value keyValue) throws ResolveTemplateException {
    return get().contains(keyValue);
  }

  @Override
  default Value call(Value methodName) throws ResolveTemplateException {
    return get().call(methodName);
  }
}
