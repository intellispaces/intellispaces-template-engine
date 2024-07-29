package tech.intellispaces.templates.template.expression.value.formal;

import tech.intellispaces.templates.exception.ResolveTemplateException;

/**
 * Formal expression value.
 *
 * <p>This interface represents user's views of the expression value.
 */
public interface FormalValue {

  /**
   * Value typename.
   */
  FormalValue typename();

  /**
   * Cast this value to boolean.
   *
   * @throws ResolveTemplateException throws if value can't be cast to boolean.
   */
  FormalValue asBoolean() throws ResolveTemplateException;

  /**
   * Cast this value to integer.
   *
   * @throws ResolveTemplateException throws if value can't be cast to integer.
   */
  FormalValue asInteger() throws ResolveTemplateException;

  /**
   * Cast this value to real.
   *
   * @throws ResolveTemplateException throws if value can't be cast to real.
   */
  FormalValue asReal() throws ResolveTemplateException;

  /**
   * Cast this value to string.
   *
   * @throws ResolveTemplateException throws if value can't be cast to string.
   */
  FormalValue asString() throws ResolveTemplateException;

  /**
   * Cast this value to list.
   *
   * @throws ResolveTemplateException throws if value can't be cast to list.
   */
  FormalValue asList() throws ResolveTemplateException;

  /**
   * Cast this value to map.
   *
   * @throws ResolveTemplateException throws if value can't be cast to map.
   */
  FormalValue asMap() throws ResolveTemplateException;

  FormalValue isVoid();

  FormalValue eq(FormalValue other) throws ResolveTemplateException;

  FormalValue isEmpty() throws ResolveTemplateException;

  FormalValue isNotEmpty() throws ResolveTemplateException;

  FormalValue isBlank() throws ResolveTemplateException;

  FormalValue isNotBlank() throws ResolveTemplateException;

  FormalValue capitalizeFirstLetter() throws ResolveTemplateException;

  FormalValue invert() throws ResolveTemplateException;

  FormalValue get(FormalValue key) throws ResolveTemplateException;

  FormalValue find(FormalValue value) throws ResolveTemplateException;

  FormalValue index() throws ResolveTemplateException;

  FormalValue isFirst() throws ResolveTemplateException;

  FormalValue isNotFirst() throws ResolveTemplateException;

  FormalValue isLast() throws ResolveTemplateException;

  FormalValue isNotLast() throws ResolveTemplateException;
}
