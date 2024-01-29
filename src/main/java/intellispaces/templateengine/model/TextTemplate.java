package intellispaces.templateengine.model;

import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;

/**
 * Text template.
 */
public interface TextTemplate {

  /**
   * Template elements.
   */
  List<TemplateElement> elements();
}
