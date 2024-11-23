package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
