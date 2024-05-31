package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
