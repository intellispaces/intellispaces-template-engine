package tech.intellispaces.templates.element;

import tech.intellispaces.templates.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
