package tech.intellispaces.templates.template.element;

public interface ForeachMarkers {

  static ForeachMarkerBuilder build() {
    return new ForeachMarkerBuilder();
  }
}
