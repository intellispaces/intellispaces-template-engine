package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
