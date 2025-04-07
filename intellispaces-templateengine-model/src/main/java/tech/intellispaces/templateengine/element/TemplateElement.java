package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.value.Value;

import java.util.Map;

/**
 * Template element.
 */
public interface TemplateElement {

  /**
   * Element type.
   */
  TemplateElementType type();

  /**
   * Element context.
   */
  TemplateElementContext context();

  /**
   * Resolves template element to string.
   *
   * @param variables resolving variables.
   * @return string.
   * @throws ResolveTemplateException throws if template element can't be resolved to string.
   */
  String resolve(Map<String, Value> variables) throws ResolveTemplateException;
}
