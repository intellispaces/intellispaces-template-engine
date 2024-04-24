package intellispaces.templateengine.template;

import intellispaces.templateengine.element.TemplateElement;

import java.util.List;

class TemplateImpl implements Template {
  private final List<TemplateElement> elements;

  TemplateImpl(List<TemplateElement> elements) {
    this.elements = elements;
  }

  @Override
  public List<TemplateElement> elements() {
    return elements;
  }
}
