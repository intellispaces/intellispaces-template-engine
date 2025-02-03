package tech.intellispaces.commons.templateengine.element;

public interface ElementContexts {

  static ElementContextBuilder build() {
    return new ElementContextBuilder();
  }
}
