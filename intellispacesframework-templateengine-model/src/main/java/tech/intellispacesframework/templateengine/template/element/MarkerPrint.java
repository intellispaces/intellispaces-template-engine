package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
