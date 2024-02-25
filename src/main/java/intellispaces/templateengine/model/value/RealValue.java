package intellispaces.templateengine.model.value;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.object.value.ValueTypes;

/**
 * Real number value.
 */
public interface RealValue extends Value {

  double get();

  @Override
  default ValueType type() {
    return ValueTypes.Real;
  }

  @Override
  RealValue invert() throws ResolveTemplateException;
}
