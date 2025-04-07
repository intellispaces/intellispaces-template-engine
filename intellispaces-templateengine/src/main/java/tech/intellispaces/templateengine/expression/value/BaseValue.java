package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.value.api.ValueApi;

public abstract class BaseValue implements Value {

  @Override
  public BooleanValue eq(ValueApi other) throws ResolveTemplateException {
    if (other instanceof Value) {
      return eq((Value) other);
    }
    throw UnexpectedExceptions.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  @Override
  public BooleanValue eqAnyOf(ValueApi value1, ValueApi value2) throws ResolveTemplateException {
    if (value1 instanceof Value && value2 instanceof Value) {
      return eqAnyOf((Value) value1, (Value) value2);
    }
    throw UnexpectedExceptions.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  @Override
  public Value get(ValueApi key) throws ResolveTemplateException {
    if (key instanceof Value) {
      return get((Value) key);
    }
    throw UnexpectedExceptions.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  @Override
  public Value find(ValueApi element) throws ResolveTemplateException {
    if (element instanceof Value) {
      return find((Value) element);
    }
    throw UnexpectedExceptions.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }
}
