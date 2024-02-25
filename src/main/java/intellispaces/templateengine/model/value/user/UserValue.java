package intellispaces.templateengine.model.value.user;

import intellispaces.templateengine.exception.ResolveTemplateException;

/**
 * Expression value in user context.<p/>
 *
 * This interface represents user's views of the value.
 */
public interface UserValue {

  /**
   * Value typename.
   */
  UserValue typename();

  /**
   * Cast this value to boolean.
   *
   * @throws ResolveTemplateException throws if value can't be cast to boolean.
   */
  UserValue asBoolean() throws ResolveTemplateException;

  /**
   * Cast this value to integer.
   *
   * @throws ResolveTemplateException throws if value can't be cast to integer.
   */
  UserValue asInteger() throws ResolveTemplateException;

  /**
   * Cast this value to real.
   *
   * @throws ResolveTemplateException throws if value can't be cast to real.
   */
  UserValue asReal() throws ResolveTemplateException;

  /**
   * Cast this value to string.
   *
   * @throws ResolveTemplateException throws if value can't be cast to string.
   */
  UserValue asString() throws ResolveTemplateException;

  /**
   * Cast this value to list.
   *
   * @throws ResolveTemplateException throws if value can't be cast to list.
   */
  UserValue asList() throws ResolveTemplateException;

  /**
   * Cast this value to map.
   *
   * @throws ResolveTemplateException throws if value can't be cast to map.
   */
  UserValue asMap() throws ResolveTemplateException;

  UserValue isVoid();

  UserValue eq(UserValue other) throws ResolveTemplateException;

  UserValue isEmpty() throws ResolveTemplateException;

  UserValue isBlank() throws ResolveTemplateException;

  UserValue capitalizeFirstLetter() throws ResolveTemplateException;

  UserValue invert() throws ResolveTemplateException;

  UserValue fetch(UserValue key) throws ResolveTemplateException;

  UserValue find(UserValue value) throws ResolveTemplateException;

  UserValue index() throws ResolveTemplateException;

  UserValue isFirst() throws ResolveTemplateException;

  UserValue isLast() throws ResolveTemplateException;
}
