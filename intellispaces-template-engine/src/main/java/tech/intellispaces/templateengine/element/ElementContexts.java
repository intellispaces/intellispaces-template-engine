package tech.intellispaces.templateengine.element;

public interface ElementContexts {

  static ElementContextBuilder build() {
    return new ElementContextBuilder();
  }
}
