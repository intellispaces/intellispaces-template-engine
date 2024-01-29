package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.StringValueBuilder;
import intellispaces.templateengine.exception.ResolveTemplateException;

public interface Value {

  ValueType type();

  /**
   * Value type name.<p/>
   *
   * The class {@link StringValue} is used as result.
   */
  default StringValue typename() {
    return StringValueBuilder.build(type().typename());
  }

  default BooleanValue eq(Value other) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}", typename().get());
  }

  default BooleanValue isEmpty() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Expected type is List or Map. Actual {}" + typename().get());
  }

  default BooleanValue isBlank() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Expected type is String. Actual {}" + typename().get());
  }

  default StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Expected type is String. Actual {}" + typename().get());
  }

  default BooleanValue not() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Expected type is boolean. Actual {}" + typename().get());
  }

  default Value at(Value indexValue) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  /**
   * Match value.
   *
   * @param keyValue the key element.
   * @return value's element.
   * @throws ResolveTemplateException
   */
  default Value match(Value keyValue) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  /**
   * Gets value's element by key.
   *
   * @param keyValue the element key.
   * @return value's element.
   * @throws ResolveTemplateException
   */
  default Value get(Value keyValue) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  default BooleanValue contains(Value keyValue) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  default Value call(Value methodName) throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  default IntegerValue index() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  default BooleanValue isFirst() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }

  default BooleanValue isLast() throws ResolveTemplateException {
    throw new ResolveTemplateException("Not expected template parameter type. Actual {}" + typename().get());
  }
}
