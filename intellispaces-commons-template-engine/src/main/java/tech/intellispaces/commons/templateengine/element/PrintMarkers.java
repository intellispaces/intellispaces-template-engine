package tech.intellispaces.commons.templateengine.element;

public interface PrintMarkers {

  static PrintMarkerBuilder build() {
    return new PrintMarkerBuilder();
  }
}
