package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
