package tech.intellispaces.framework.templateengine.template.element;

import java.util.Objects;

public final class TextElementBuilder {
  private TemplateElementContext context;
  private String text;

  public static TextElementBuilder get() {
    return new TextElementBuilder();
  }

  public TextElementBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public TextElementBuilder text(String text) {
    this.text = text;
    return this;
  }

  public TextElement build() {
    validate();
    return new TextElementImpl(context, text);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(text);
  }

  private TextElementBuilder() {}
}
