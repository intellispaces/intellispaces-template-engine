package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
