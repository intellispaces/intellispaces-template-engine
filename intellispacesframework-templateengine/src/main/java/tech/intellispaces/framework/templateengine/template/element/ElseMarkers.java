package tech.intellispaces.framework.templateengine.template.element;

public interface ElseMarkers {

  static ElseMarkerBuilder build() {
    return new ElseMarkerBuilder();
  }
}
