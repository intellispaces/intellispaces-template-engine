package tech.intellispaces.templateengine;

import tech.intellispaces.templateengine.exception.ParseTemplateException;
import tech.intellispaces.templateengine.template.Template;
import tech.intellispaces.templateengine.template.Templates;

public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException  {
    return Templates.of(source);
  }
}
