package tech.intellispaces.framework.templateengine.template;

import tech.intellispaces.framework.templateengine.template.element.TemplateElement;

import java.util.List;

public interface Templates {

  static Template get(List<TemplateElement> elements) {
    return new TemplateImpl(elements != null ? List.copyOf(elements) : List.of());
  }
}
