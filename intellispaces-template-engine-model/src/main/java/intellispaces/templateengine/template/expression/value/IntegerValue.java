package intellispaces.templateengine.template.expression.value;

import intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
