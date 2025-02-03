package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;

/**
 * Real number value.
 */
public interface RealValue extends Value {

  double get();

  @Override
  RealValue invert() throws ResolveTemplateException;
}
