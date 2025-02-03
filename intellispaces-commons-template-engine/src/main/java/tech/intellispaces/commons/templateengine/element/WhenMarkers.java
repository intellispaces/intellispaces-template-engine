package tech.intellispaces.commons.templateengine.element;

public interface WhenMarkers {

  static WhenMarkerBuilder build() {
    return new WhenMarkerBuilder();
  }
}
