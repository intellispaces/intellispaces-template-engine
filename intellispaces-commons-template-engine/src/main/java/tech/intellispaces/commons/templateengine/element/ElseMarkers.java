package tech.intellispaces.commons.templateengine.element;

public interface ElseMarkers {

  static ElseMarkerBuilder build() {
    return new ElseMarkerBuilder();
  }
}
