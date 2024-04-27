package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;

/**
 * Real number value.
 */
public interface RealValue extends Value {

  double get();

  @Override
  RealValue invert() throws ResolveTemplateException;
}
