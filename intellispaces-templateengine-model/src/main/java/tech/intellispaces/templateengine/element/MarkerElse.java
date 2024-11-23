package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
