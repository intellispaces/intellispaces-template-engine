package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
