package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
