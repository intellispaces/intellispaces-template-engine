package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

/**
 * Marker <foreach>.
 */
public interface MarkerForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

}
