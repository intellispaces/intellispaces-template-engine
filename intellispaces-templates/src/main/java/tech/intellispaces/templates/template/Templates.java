package tech.intellispaces.templates.template;

import tech.intellispaces.templates.template.element.TemplateElement;

import java.util.List;

public interface Templates {

  static Template of(List<TemplateElement> elements) {
    return new TemplateImpl(elements != null ? List.copyOf(elements) : List.of());
  }
}
