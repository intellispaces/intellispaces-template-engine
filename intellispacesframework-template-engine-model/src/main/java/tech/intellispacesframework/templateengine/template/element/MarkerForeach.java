package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
