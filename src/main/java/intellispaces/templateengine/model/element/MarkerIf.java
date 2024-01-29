package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * The "if" marker.
 */
public interface MarkerIf extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerIf;
  }

  Expression conditionExpression();

  @Override
  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, params);
  }
}
