package tech.intellispacesframework.templateengine.template;

import tech.intellispacesframework.templateengine.template.element.TemplateElement;

import java.util.List;

public interface TemplateBuilder {

  static Template build(List<TemplateElement> elements) {
    return new TemplateImpl(elements != null ? List.copyOf(elements) : List.of());
  }
}
