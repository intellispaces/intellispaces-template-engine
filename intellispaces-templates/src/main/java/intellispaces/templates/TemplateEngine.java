package intellispaces.templates;

import intellispaces.templates.exception.ParseTemplateException;
import intellispaces.templates.template.Template;
import intellispaces.templates.template.Templates;

public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException  {
    return Templates.of(source);
  }
}
