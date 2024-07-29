package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
