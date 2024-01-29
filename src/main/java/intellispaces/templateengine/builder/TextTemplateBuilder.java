package intellispaces.templateengine.builder;

import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;

public final class TextTemplateBuilder {

  public static TextTemplate build(List<TemplateElement> elements) {
    return new TextTemplateImpl(elements != null ? List.copyOf(elements) : List.of());
  }

  private TextTemplateBuilder() {}
}
