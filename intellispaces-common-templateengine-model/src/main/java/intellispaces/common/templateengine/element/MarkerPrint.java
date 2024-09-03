package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
