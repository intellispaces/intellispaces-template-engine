package intellispaces.templateengine.builder;

import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;

class TextTemplateImpl implements TextTemplate {
  private final List<TemplateElement> elements;

  TextTemplateImpl(List<TemplateElement> elements) {
    this.elements = elements;
  }

  @Override
  public List<TemplateElement> elements() {
    return elements;
  }
}
