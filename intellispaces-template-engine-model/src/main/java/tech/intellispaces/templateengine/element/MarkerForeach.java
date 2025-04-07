package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
