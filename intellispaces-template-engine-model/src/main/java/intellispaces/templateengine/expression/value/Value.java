package intellispaces.templateengine.expression.value;

import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.expression.value.user.UserValue;

/**
 * Expression value.
 */
public interface Value extends UserValue {

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
  default BooleanValue eq(UserValue other) throws ResolveTemplateException {
    if (other instanceof Value) {
      return eq((Value) other);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {} class", Value.class.getSimpleName());
  }

  BooleanValue eq(Value other) throws ResolveTemplateException;

  @Override
  BooleanValue isEmpty() throws ResolveTemplateException;

  @Override
  BooleanValue isBlank() throws ResolveTemplateException;

  @Override
  StringValue capitalizeFirstLetter() throws ResolveTemplateException;

  @Override
  Value invert() throws ResolveTemplateException;

  @Override
  default Value fetch(UserValue key) throws ResolveTemplateException {
    if (key instanceof Value) {
      return fetch((Value) key);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {} class", Value.class.getSimpleName());
  }

  Value fetch(Value key) throws ResolveTemplateException;

  @Override
  default Value find(UserValue element) throws ResolveTemplateException {
    if (element instanceof Value) {
      return find((Value) element);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {} class", Value.class.getSimpleName());
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
  Value index() throws ResolveTemplateException;

  @Override
  Value isFirst() throws ResolveTemplateException;

  @Override
  Value isLast() throws ResolveTemplateException;
}
