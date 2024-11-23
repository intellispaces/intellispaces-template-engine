package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
