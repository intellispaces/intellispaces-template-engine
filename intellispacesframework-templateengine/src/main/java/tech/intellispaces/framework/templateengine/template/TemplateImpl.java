package tech.intellispaces.framework.templateengine.template;

import tech.intellispaces.framework.templateengine.template.element.TemplateElement;

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
