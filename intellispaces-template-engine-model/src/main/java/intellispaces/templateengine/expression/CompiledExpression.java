package intellispaces.templateengine.expression;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.expression.value.Value;

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
