package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
