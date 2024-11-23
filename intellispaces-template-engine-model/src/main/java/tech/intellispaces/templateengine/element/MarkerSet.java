package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
