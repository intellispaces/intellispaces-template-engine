package tech.intellispaces.templates;

import tech.intellispaces.templates.exception.ParseTemplateException;
import tech.intellispaces.templates.template.Template;
import tech.intellispaces.templates.template.TemplateFunctions;

/**
 * Template facade functions.
 */
public interface TemplateEngine {

  static Template of(String source) throws ParseTemplateException {
    return TemplateFunctions.parseTemplate(source);
  }
}
