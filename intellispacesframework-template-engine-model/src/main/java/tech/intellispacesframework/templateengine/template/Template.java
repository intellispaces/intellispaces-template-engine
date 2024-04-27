package tech.intellispacesframework.templateengine.template;

import tech.intellispacesframework.templateengine.template.element.TemplateElement;

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
