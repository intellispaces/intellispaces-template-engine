package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
