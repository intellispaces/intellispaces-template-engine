package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.value.api.ValueApi;

/**
 * Expression value.
 */
public interface Value extends ValueApi {

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
  BooleanValue isNotVoid();

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
  BooleanValue eq(ValueApi other) throws ResolveTemplateException;

  BooleanValue eq(Value other) throws ResolveTemplateException;

  @Override
  BooleanValue eqAnyOf(ValueApi value1, ValueApi value2) throws ResolveTemplateException;

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
  Value get(ValueApi key) throws ResolveTemplateException;

  Value get(Value key) throws ResolveTemplateException;

  @Override
  Value find(ValueApi element) throws ResolveTemplateException;

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
