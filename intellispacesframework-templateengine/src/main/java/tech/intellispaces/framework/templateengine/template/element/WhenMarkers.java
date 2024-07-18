package tech.intellispaces.framework.templateengine.template.element;

public interface WhenMarkers {

  static WhenMarkerBuilder build() {
    return new WhenMarkerBuilder();
  }
}
