package tech.intellispaces.templateengine.element;

public interface EndMarkers {

  static EndMarkerBuilder build() {
    return new EndMarkerBuilder();
  }
}
