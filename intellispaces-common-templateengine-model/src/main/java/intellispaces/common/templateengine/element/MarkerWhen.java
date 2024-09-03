package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
