package tech.intellispaces.templateengine.expression.value.api;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Expression value applied representation.
 *
 * <p>This interface represents user's views of the expression value.
 */
public interface ValueApi {

  /**
   * Value typename.
   */
  ValueApi typename();

  /**
   * Cast this value to boolean.
   *
   * @throws ResolveTemplateException throws if value can't be cast to boolean.
   */
  ValueApi asBoolean() throws ResolveTemplateException;

  /**
   * Cast this value to integer.
   *
   * @throws ResolveTemplateException throws if value can't be cast to integer.
   */
  ValueApi asInteger() throws ResolveTemplateException;

  /**
   * Cast this value to real.
   *
   * @throws ResolveTemplateException throws if value can't be cast to real.
   */
  ValueApi asReal() throws ResolveTemplateException;

  /**
   * Cast this value to string.
   *
   * @throws ResolveTemplateException throws if value can't be cast to string.
   */
  ValueApi asString() throws ResolveTemplateException;

  /**
   * Cast this value to list.
   *
   * @throws ResolveTemplateException throws if value can't be cast to list.
   */
  ValueApi asList() throws ResolveTemplateException;

  /**
   * Cast this value to map.
   *
   * @throws ResolveTemplateException throws if value can't be cast to map.
   */
  ValueApi asMap() throws ResolveTemplateException;

  ValueApi isVoid();

  ValueApi eq(ValueApi other) throws ResolveTemplateException;

  ValueApi eqAnyOf(ValueApi value1, ValueApi value2) throws ResolveTemplateException;

  ValueApi isEmpty() throws ResolveTemplateException;

  ValueApi isNotEmpty() throws ResolveTemplateException;

  ValueApi isBlank() throws ResolveTemplateException;

  ValueApi isNotBlank() throws ResolveTemplateException;

  ValueApi capitalizeFirstLetter() throws ResolveTemplateException;

  ValueApi invert() throws ResolveTemplateException;

  ValueApi get(ValueApi key) throws ResolveTemplateException;

  ValueApi find(ValueApi value) throws ResolveTemplateException;

  ValueApi index() throws ResolveTemplateException;

  ValueApi isFirst() throws ResolveTemplateException;

  ValueApi isNotFirst() throws ResolveTemplateException;

  ValueApi isLast() throws ResolveTemplateException;

  ValueApi isNotLast() throws ResolveTemplateException;

  ValueApi size() throws ResolveTemplateException;
}
