package tech.intellispaces.framework.templateengine.template.element;

public interface ForeachMarkers {

  static ForeachMarkerBuilder build() {
    return new ForeachMarkerBuilder();
  }
}
