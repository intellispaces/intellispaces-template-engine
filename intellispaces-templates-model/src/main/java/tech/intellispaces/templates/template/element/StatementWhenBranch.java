package tech.intellispaces.templates.template.element;

import tech.intellispaces.templates.template.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
