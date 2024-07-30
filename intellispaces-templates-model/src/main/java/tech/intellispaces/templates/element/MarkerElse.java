package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
