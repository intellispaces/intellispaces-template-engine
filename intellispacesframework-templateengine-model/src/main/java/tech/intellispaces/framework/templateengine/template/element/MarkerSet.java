package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
