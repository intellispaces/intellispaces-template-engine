package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
