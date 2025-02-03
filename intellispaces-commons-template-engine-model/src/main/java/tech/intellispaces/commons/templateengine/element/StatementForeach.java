package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

import java.util.List;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();
}
