package tech.intellispaces.commons.templateengine.element;

import java.util.List;

/**
 * Marker <format>.
 */
public interface MarkerFormat extends TemplateElement {

  List<MarkerFormatType> types();
}
