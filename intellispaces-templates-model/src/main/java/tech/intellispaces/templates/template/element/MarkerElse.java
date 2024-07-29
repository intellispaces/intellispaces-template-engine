package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
