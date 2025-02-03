package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
