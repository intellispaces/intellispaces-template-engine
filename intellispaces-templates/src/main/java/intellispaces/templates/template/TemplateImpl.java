package intellispaces.templates.template;

import intellispaces.templates.element.TemplateElement;
import intellispaces.templates.exception.ResolveTemplateException;

import java.util.List;
import java.util.Map;

class TemplateImpl implements Template {
  private final List<TemplateElement> elements;

  TemplateImpl(List<TemplateElement> elements) {
    this.elements = elements;
  }

  @Override
  public List<TemplateElement> elements() {
    return elements;
  }

  @Override
  public String resolve(Map<String, Object> variables) throws ResolveTemplateException {
    return TemplateFunctions.resolveTemplate(this, variables);
  }
}
