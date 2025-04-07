package tech.intellispaces.templateengine.element;

import java.util.Objects;

public final class TextElementBuilder {
  private TemplateElementContext context;
  private String text;

  TextElementBuilder() {}

  public TextElementBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public TextElementBuilder text(String text) {
    this.text = text;
    return this;
  }

  public TextElement get() {
    validate();
    return new TextElementImpl(context, text);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(text);
  }
}
