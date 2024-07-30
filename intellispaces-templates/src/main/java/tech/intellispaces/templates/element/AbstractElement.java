package tech.intellispaces.templates.element;

abstract class AbstractElement implements TemplateElement {
  private final TemplateElementContext context;

  AbstractElement(TemplateElementContext context) {
    this.context = context;
  }

  @Override
  public TemplateElementContext context() {
    return context;
  }
}
