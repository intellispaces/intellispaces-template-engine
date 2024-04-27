package tech.intellispacesframework.templateengine.template.expression;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.value.Value;

/**
 * Compiled expression.
 */
public interface CompiledExpression {

  /**
   * Resolves expression to value.
   *
   * @param operands expression operand values.
   * @return resolved value.
   * @throws ResolveTemplateException throws when expression can't be resolved.
   */
  Value resolve(Value[] operands) throws ResolveTemplateException;
}
