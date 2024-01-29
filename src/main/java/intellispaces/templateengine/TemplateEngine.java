package intellispaces.templateengine;

import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.parse.ParseFunctions;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.TextTemplate;

import java.util.Map;

/**
 * Template engine facade functions.
 */
public interface TemplateEngine {

  static TextTemplate parseTemplate(String source) throws ParseTemplateException {
    return ParseFunctions.parse(source);
  }

  static String resolveTemplate(TextTemplate template, Map<String, Object> params) throws ResolveTemplateException {
    return ResolveFunctions.resolve(template, params);
  }
}
