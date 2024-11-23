package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Boolean value.
 */
public interface BooleanValue extends Value {

  boolean get();

  @Override
  BooleanValue invert() throws ResolveTemplateException;
}
