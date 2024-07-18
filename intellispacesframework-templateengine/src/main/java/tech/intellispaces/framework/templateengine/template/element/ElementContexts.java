package tech.intellispaces.framework.templateengine.template.element;

public interface ElementContexts {

  static ElementContextBuilder build() {
    return new ElementContextBuilder();
  }
}
