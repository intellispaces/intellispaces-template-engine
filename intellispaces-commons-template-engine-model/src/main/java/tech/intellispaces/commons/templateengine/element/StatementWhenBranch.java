package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
