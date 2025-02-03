package tech.intellispaces.commons.templateengine.element;

public interface ForeachMarkers {

  static ForeachMarkerBuilder build() {
    return new ForeachMarkerBuilder();
  }
}
