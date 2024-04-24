package intellispaces.templateengine;

import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.ParseTemplateFunctions;
import intellispaces.templateengine.template.ResolveTemplateFunctions;
import intellispaces.templateengine.template.Template;

import java.util.Map;

/**
 * Template engine facade functions.
 */
public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException {
    return ParseTemplateFunctions.parseTemplate(source);
  }

  static String resolveTemplate(Template template, Map<String, Object> variables) throws ResolveTemplateException {
    return ResolveTemplateFunctions.resolveTemplate(template, variables);
  }
}
