package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.Expression;

import java.util.List;

public interface StatementWhenBranch {

  Expression condition();

  List<TemplateElement> subElements();
}
