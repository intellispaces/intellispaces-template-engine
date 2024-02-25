package intellispaces.templateengine.model.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.object.value.ValueTypes;

/**
 * Boolean value.
 */
public interface BooleanValue extends Value {

  boolean get();

  @Override
  default ValueType type() {
    return ValueTypes.Boolean;
  }

  @Override
  BooleanValue invert() throws ResolveTemplateException;
}
