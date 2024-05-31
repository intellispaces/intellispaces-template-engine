package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

/**
 * Boolean value.
 */
public interface BooleanValue extends Value {

  boolean get();

  @Override
  BooleanValue invert() throws ResolveTemplateException;
}
