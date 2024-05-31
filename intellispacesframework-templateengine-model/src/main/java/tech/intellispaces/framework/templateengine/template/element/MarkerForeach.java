package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
