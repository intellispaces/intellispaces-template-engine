package intellispaces.common.templateengine.element;

public interface ForeachMarkers {

  static ForeachMarkerBuilder build() {
    return new ForeachMarkerBuilder();
  }
}
