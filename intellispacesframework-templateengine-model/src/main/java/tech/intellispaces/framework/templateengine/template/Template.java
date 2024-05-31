package tech.intellispaces.framework.templateengine.template;

import tech.intellispaces.framework.templateengine.template.element.TemplateElement;

import java.util.List;

/**
 * Text template.
 */
public interface Template {

  /**
   * Template elements.
   */
  List<TemplateElement> elements();
}
