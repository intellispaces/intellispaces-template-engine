package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
