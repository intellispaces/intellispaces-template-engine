package tech.intellispaces.templateengine.element;

public interface ElseMarkers {

  static ElseMarkerBuilder build() {
    return new ElseMarkerBuilder();
  }
}
