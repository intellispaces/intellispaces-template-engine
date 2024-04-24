package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

/**
 * Marker <print>.
 */
public interface MarkerPrint extends TemplateElement {

  Expression outputExpression();
}
