package tech.intellispaces.templateengine.element;

public interface PrintMarkers {

  static PrintMarkerBuilder build() {
    return new PrintMarkerBuilder();
  }
}
