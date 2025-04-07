package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
