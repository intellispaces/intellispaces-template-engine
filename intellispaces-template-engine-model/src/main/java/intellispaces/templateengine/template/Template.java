package intellispaces.templateengine.template;

import intellispaces.templateengine.element.TemplateElement;

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