package intellispaces.templateengine.function.compile;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.Value;

/**
 * Compiled expression statement.
 */
public interface CompiledStatement {

  /**
   * Resolves expression.
   *
   * @param operands expression operand values.
   * @return expression result value.
   * @throws ResolveTemplateException throws when expression can't be resolved.
   */
  Value resolve(Value[] operands) throws ResolveTemplateException;
}
