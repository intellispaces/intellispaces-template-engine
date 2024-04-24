package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
