package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.value.Value;

import java.util.List;
import java.util.Map;

public interface StatementIf extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.StatementIf;
  }

  List<Branch> conditionBranches();

  List<TemplateElement> defaultBranch();

  @Override
  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, params);
  }

  record Branch(Expression conditionExpression, List<TemplateElement> subElements) {}
}
