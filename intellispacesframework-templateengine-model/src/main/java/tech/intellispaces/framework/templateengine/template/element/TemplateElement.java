package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;
import tech.intellispaces.framework.templateengine.template.source.position.Position;

import java.util.Map;

/**
 * Template element.
 */
public interface TemplateElement {

  /**
   * Position of the element in template source.
   */
  Position position();

  /**
   * Element type.
   */
  TemplateElementType type();

  /**
   * Resolves template element to string.
   *
   * @param variables resolving variables.
   * @return string.
   * @throws ResolveTemplateException throws if template element can't be resolved to string.
   */
  String resolve(Map<String, Value> variables) throws ResolveTemplateException;
}
