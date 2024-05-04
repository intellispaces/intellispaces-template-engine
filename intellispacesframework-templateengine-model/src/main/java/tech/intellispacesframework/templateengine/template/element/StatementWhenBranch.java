package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
