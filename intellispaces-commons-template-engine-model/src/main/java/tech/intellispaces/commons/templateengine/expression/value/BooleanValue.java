package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;

/**
 * Boolean value.
 */
public interface BooleanValue extends Value {

  boolean get();

  @Override
  BooleanValue invert() throws ResolveTemplateException;
}
