package tech.intellispaces.templateengine.element;

public interface WhenMarkers {

  static WhenMarkerBuilder build() {
    return new WhenMarkerBuilder();
  }
}
