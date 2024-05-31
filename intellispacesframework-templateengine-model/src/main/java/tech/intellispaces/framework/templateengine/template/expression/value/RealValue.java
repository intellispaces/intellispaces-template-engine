package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

/**
 * Real number value.
 */
public interface RealValue extends Value {

  double get();

  @Override
  RealValue invert() throws ResolveTemplateException;
}
