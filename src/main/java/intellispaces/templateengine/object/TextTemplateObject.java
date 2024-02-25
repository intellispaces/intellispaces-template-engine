package intellispaces.templateengine.object;

import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;

class TextTemplateObject implements TextTemplate {
  private final List<TemplateElement> elements;

  TextTemplateObject(List<TemplateElement> elements) {
    this.elements = elements;
  }

  @Override
  public List<TemplateElement> elements() {
    return elements;
  }
}
