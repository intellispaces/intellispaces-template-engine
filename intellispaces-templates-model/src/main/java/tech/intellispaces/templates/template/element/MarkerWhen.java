package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
