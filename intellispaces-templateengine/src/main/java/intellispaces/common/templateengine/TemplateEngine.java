package intellispaces.common.templateengine;

import intellispaces.common.templateengine.exception.ParseTemplateException;
import intellispaces.common.templateengine.template.Template;
import intellispaces.common.templateengine.template.Templates;

public interface TemplateEngine {

  static Template parseTemplate(String source) throws ParseTemplateException  {
    return Templates.of(source);
  }
}
