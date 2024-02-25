package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.value.Value;

import java.util.List;
import java.util.Map;

/**
 * The "Foreach" statement.
 */
public interface StatementForeach extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.StatementForeach;
  }

  Expression collectionExpression();

  String itemName();

  List<TemplateElement> subElements();

  @Override
  default String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, variables);
  }
}
