package intellispaces.templateengine.model.element;

import intellispaces.templateengine.model.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
