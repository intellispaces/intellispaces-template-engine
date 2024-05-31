package tech.intellispaces.framework.templateengine.template.expression;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

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
