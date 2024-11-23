package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

import java.util.List;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();
}
