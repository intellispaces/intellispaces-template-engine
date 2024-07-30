package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
