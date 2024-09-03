package intellispaces.common.templateengine.template;

import intellispaces.common.templateengine.element.TemplateElement;
import intellispaces.common.templateengine.exception.ResolveTemplateException;

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
