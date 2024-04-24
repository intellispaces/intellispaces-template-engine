package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
