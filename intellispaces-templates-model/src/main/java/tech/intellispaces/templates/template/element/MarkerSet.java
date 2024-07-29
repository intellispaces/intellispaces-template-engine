package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
