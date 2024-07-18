package tech.intellispaces.framework.templateengine.template.element;

public interface EndMarkers {

  static EndMarkerBuilder build() {
    return new EndMarkerBuilder();
  }
}
