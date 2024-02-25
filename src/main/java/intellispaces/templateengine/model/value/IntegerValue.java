package intellispaces.templateengine.model.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.object.value.ValueTypes;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  default ValueType type() {
    return ValueTypes.Integer;
  }

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
