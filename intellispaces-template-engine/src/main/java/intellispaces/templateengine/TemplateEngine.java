package intellispaces.templateengine;

import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.TemplateFunctions;
import intellispaces.templateengine.template.Template;

import java.util.Map;

/**
 * Template engine facade functions.
 */
public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException {
    return TemplateFunctions.parseTemplate(source);
  }

  static String resolveTemplate(Template template, Map<String, Object> variables) throws ResolveTemplateException {
    return TemplateFunctions.resolveTemplate(template, variables);
  }
}
