package tech.intellispaces.framework.templateengine;

import tech.intellispaces.framework.templateengine.template.Template;
import tech.intellispaces.framework.templateengine.template.TemplateFunctions;
import tech.intellispaces.framework.templateengine.exception.ParseTemplateException;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;

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
