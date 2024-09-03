package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
