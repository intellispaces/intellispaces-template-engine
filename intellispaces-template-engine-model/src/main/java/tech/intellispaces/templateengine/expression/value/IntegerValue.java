package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
