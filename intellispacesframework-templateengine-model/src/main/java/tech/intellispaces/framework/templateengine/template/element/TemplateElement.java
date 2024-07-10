package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

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
