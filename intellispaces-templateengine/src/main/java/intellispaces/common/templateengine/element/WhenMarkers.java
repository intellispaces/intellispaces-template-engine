package intellispaces.common.templateengine.element;

public interface WhenMarkers {

  static WhenMarkerBuilder build() {
    return new WhenMarkerBuilder();
  }
}
