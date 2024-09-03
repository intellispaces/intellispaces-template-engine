package intellispaces.common.templateengine.element;

public interface PrintMarkers {

  static PrintMarkerBuilder build() {
    return new PrintMarkerBuilder();
  }
}
