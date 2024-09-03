package intellispaces.common.templateengine.element;

public interface EndMarkers {

  static EndMarkerBuilder build() {
    return new EndMarkerBuilder();
  }
}
