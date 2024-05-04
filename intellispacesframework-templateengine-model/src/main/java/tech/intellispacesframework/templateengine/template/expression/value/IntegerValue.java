package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;

/**
 * Integer number value.
 */
public interface IntegerValue extends Value {

  int get();

  @Override
  IntegerValue invert() throws ResolveTemplateException;
}
