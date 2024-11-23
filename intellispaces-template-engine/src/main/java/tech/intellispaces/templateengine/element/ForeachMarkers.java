package tech.intellispaces.templateengine.element;

public interface ForeachMarkers {

  static ForeachMarkerBuilder build() {
    return new ForeachMarkerBuilder();
  }
}
