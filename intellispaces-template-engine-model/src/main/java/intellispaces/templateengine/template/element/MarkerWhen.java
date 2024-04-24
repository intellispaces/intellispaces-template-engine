package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
