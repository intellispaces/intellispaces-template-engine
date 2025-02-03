package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
