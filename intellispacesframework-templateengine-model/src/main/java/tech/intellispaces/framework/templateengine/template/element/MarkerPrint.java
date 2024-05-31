package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
