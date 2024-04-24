package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
