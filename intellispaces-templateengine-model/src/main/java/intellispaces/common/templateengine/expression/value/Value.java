package intellispaces.common.templateengine.expression.value;

import intellispaces.common.base.exception.UnexpectedViolationException;
import intellispaces.common.templateengine.exception.ResolveTemplateException;
import intellispaces.common.templateengine.expression.value.common.CommonValue;

/**
 * Expression value.
 */
public interface Value extends CommonValue {

  /**
   * Value type.
   */
  ValueType type();

  Value origin();

  @Override
  StringValue typename();

  @Override
  BooleanValue isVoid();

  @Override
  BooleanValue asBoolean() throws ResolveTemplateException;

  @Override
  IntegerValue asInteger() throws ResolveTemplateException;

  @Override
  RealValue asReal() throws ResolveTemplateException;

  @Override
  StringValue asString() throws ResolveTemplateException;

  @Override
  ListValue asList() throws ResolveTemplateException;

  @Override
  MapValue asMap() throws ResolveTemplateException;

  @Override
  default BooleanValue eq(CommonValue other) throws ResolveTemplateException {
    if (other instanceof Value) {
      return eq((Value) other);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  BooleanValue eq(Value other) throws ResolveTemplateException;

  @Override
  default BooleanValue eqAnyOf(CommonValue value1, CommonValue value2) throws ResolveTemplateException {
    if (value1 instanceof Value && value2 instanceof Value) {
      return eqAnyOf((Value) value1, (Value) value2);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  BooleanValue eqAnyOf(Value value1, Value value2) throws ResolveTemplateException;

  @Override
  BooleanValue isEmpty() throws ResolveTemplateException;

  @Override
  BooleanValue isNotEmpty() throws ResolveTemplateException;

  @Override
  BooleanValue isBlank() throws ResolveTemplateException;

  @Override
  BooleanValue isNotBlank() throws ResolveTemplateException;

  @Override
  StringValue capitalizeFirstLetter() throws ResolveTemplateException;

  @Override
  Value invert() throws ResolveTemplateException;

  @Override
  default Value get(CommonValue key) throws ResolveTemplateException {
    if (key instanceof Value) {
      return get((Value) key);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  Value get(Value key) throws ResolveTemplateException;

  @Override
  default Value find(CommonValue element) throws ResolveTemplateException {
    if (element instanceof Value) {
      return find((Value) element);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {0} class", Value.class.getSimpleName());
  }

  /**
   * Search for given element inside current value.
   *
   * <p>This operation is applicable for string, list and map.
   *
   * @param element the element.
   * @return found element or void if element is not found. If element is found then operations index, isFirst and isLast are applicable.
   * @throws ResolveTemplateException throws if operation is not applicable for this value.
   */
  Value find(Value element) throws ResolveTemplateException;

  @Override
  IntegerValue index() throws ResolveTemplateException;

  @Override
  BooleanValue isFirst() throws ResolveTemplateException;

  @Override
  BooleanValue isNotFirst() throws ResolveTemplateException;

  @Override
  BooleanValue isLast() throws ResolveTemplateException;

  @Override
  BooleanValue isNotLast() throws ResolveTemplateException;

  IntegerValue size() throws ResolveTemplateException;
}
