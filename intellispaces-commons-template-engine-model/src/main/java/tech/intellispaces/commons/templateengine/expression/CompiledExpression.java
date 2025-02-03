package tech.intellispaces.commons.templateengine.expression;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.commons.templateengine.expression.value.Value;

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
