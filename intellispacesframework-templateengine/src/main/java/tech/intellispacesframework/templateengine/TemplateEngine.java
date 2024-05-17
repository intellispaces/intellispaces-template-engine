package tech.intellispacesframework.templateengine;

import tech.intellispacesframework.templateengine.exception.ParseTemplateException;
import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.Template;
import tech.intellispacesframework.templateengine.template.TemplateFunctions;

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
