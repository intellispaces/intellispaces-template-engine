package tech.intellispaces.framework.templateengine.template.element;

import java.util.List;

/**
 * Marker <format>.
 */
public interface MarkerFormat extends TemplateElement {

  List<MarkerFormatType> types();
}
