package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
