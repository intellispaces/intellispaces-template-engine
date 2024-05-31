package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.List;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();
}
