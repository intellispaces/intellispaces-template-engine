package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

/**
 * Marker <set>.
 */
public interface MarkerSet extends TemplateElement {

  String valueName();

  Expression valueExpression();
}
