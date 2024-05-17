package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;
import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.value.formal.FormalValue;

/**
 * Expression value.
 */
public interface Value extends FormalValue {

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
  default BooleanValue eq(FormalValue other) throws ResolveTemplateException {
    if (other instanceof Value) {
      return eq((Value) other);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {} class", Value.class.getSimpleName());
  }

  BooleanValue eq(Value other) throws ResolveTemplateException;

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
  default Value get(FormalValue key) throws ResolveTemplateException {
    if (key instanceof Value) {
      return get((Value) key);
    }
    throw UnexpectedViolationException.withMessage("Expected instance of the {} class", Value.class.getSimpleName());
  }

  Value get(Value key) throws ResolveTemplateException;

  @Override
  default Value find(FormalValue element) throws ResolveTemplateException {
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
  IntegerValue index() throws ResolveTemplateException;

  @Override
  BooleanValue isFirst() throws ResolveTemplateException;

  @Override
  BooleanValue isNotFirst() throws ResolveTemplateException;

  @Override
  BooleanValue isLast() throws ResolveTemplateException;

  @Override
  BooleanValue isNotLast() throws ResolveTemplateException;
}
