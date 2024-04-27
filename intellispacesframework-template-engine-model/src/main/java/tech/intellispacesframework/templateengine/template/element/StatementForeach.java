package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

import java.util.List;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();
}
