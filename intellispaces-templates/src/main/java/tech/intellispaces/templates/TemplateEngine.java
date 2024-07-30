package tech.intellispaces.templates;

import tech.intellispaces.templates.exception.ParseTemplateException;
import tech.intellispaces.templates.template.Template;
import tech.intellispaces.templates.template.Templates;

public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException  {
    return Templates.of(source);
  }
}
