package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
