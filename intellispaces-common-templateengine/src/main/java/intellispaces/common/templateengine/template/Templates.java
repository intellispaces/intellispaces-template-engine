package intellispaces.common.templateengine.template;

import intellispaces.common.templateengine.element.TemplateElement;
import intellispaces.common.templateengine.exception.ParseTemplateException;

import java.util.List;

public interface Templates {

  static Template of(String source) throws ParseTemplateException {
    return TemplateFunctions.parseTemplate(source);
  }

  static Template of(List<TemplateElement> elements) {
    return new TemplateImpl(elements != null ? List.copyOf(elements) : List.of());
  }
}
