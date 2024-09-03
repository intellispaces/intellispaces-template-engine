package intellispaces.common.templateengine.element;

public interface ElseMarkers {

  static ElseMarkerBuilder build() {
    return new ElseMarkerBuilder();
  }
}
