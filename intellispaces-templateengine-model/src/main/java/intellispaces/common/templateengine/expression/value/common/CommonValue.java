package intellispaces.common.templateengine.expression.value.common;

import intellispaces.common.templateengine.exception.ResolveTemplateException;

/**
 * Common expression value.
 *
 * <p>This interface represents user's views of the expression value.
 */
public interface CommonValue {

  /**
   * Value typename.
   */
  CommonValue typename();

  /**
   * Cast this value to boolean.
   *
   * @throws ResolveTemplateException throws if value can't be cast to boolean.
   */
  CommonValue asBoolean() throws ResolveTemplateException;

  /**
   * Cast this value to integer.
   *
   * @throws ResolveTemplateException throws if value can't be cast to integer.
   */
  CommonValue asInteger() throws ResolveTemplateException;

  /**
   * Cast this value to real.
   *
   * @throws ResolveTemplateException throws if value can't be cast to real.
   */
  CommonValue asReal() throws ResolveTemplateException;

  /**
   * Cast this value to string.
   *
   * @throws ResolveTemplateException throws if value can't be cast to string.
   */
  CommonValue asString() throws ResolveTemplateException;

  /**
   * Cast this value to list.
   *
   * @throws ResolveTemplateException throws if value can't be cast to list.
   */
  CommonValue asList() throws ResolveTemplateException;

  /**
   * Cast this value to map.
   *
   * @throws ResolveTemplateException throws if value can't be cast to map.
   */
  CommonValue asMap() throws ResolveTemplateException;

  CommonValue isVoid();

  CommonValue eq(CommonValue other) throws ResolveTemplateException;

  CommonValue eqAnyOf(CommonValue value1, CommonValue value2) throws ResolveTemplateException;

  CommonValue isEmpty() throws ResolveTemplateException;

  CommonValue isNotEmpty() throws ResolveTemplateException;

  CommonValue isBlank() throws ResolveTemplateException;

  CommonValue isNotBlank() throws ResolveTemplateException;

  CommonValue capitalizeFirstLetter() throws ResolveTemplateException;

  CommonValue invert() throws ResolveTemplateException;

  CommonValue get(CommonValue key) throws ResolveTemplateException;

  CommonValue find(CommonValue value) throws ResolveTemplateException;

  CommonValue index() throws ResolveTemplateException;

  CommonValue isFirst() throws ResolveTemplateException;

  CommonValue isNotFirst() throws ResolveTemplateException;

  CommonValue isLast() throws ResolveTemplateException;

  CommonValue isNotLast() throws ResolveTemplateException;

  CommonValue size() throws ResolveTemplateException;
}
