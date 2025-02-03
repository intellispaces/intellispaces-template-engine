package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
