package intellispaces.templateengine.expression.value;

import intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Real number value.
 */
public interface RealValue extends Value {

  double get();

  @Override
  RealValue invert() throws ResolveTemplateException;
}
