package intellispaces.common.templateengine.element;

public interface ElementContexts {

  static ElementContextBuilder build() {
    return new ElementContextBuilder();
  }
}
