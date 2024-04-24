package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
