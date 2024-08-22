package intellispaces.templates.expression.value;

import intellispaces.templates.exception.ResolveTemplateException;

/**
 * Boolean value.
 */
public interface BooleanValue extends Value {

  boolean get();

  @Override
  BooleanValue invert() throws ResolveTemplateException;
}
