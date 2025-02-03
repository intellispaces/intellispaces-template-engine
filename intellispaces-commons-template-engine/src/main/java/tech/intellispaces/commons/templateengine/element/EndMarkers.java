package tech.intellispaces.commons.templateengine.element;

public interface EndMarkers {

  static EndMarkerBuilder build() {
    return new EndMarkerBuilder();
  }
}
