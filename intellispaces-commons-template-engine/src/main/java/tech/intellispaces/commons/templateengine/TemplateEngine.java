package tech.intellispaces.commons.templateengine;

import tech.intellispaces.commons.templateengine.exception.ParseTemplateException;
import tech.intellispaces.commons.templateengine.template.Template;
import tech.intellispaces.commons.templateengine.template.Templates;

public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException  {
    return Templates.of(source);
  }
}
