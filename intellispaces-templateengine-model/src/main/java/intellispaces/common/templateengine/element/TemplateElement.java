package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.exception.ResolveTemplateException;
import intellispaces.common.templateengine.expression.value.Value;

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
