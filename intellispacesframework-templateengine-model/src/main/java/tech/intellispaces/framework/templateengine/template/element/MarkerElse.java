package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
