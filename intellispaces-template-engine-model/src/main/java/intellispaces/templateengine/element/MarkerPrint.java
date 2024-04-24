package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
