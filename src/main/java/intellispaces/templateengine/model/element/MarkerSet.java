package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * The "Set" marker.
 */
public interface MarkerSet extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerSet;
  }

  String valueName();

  Expression valueExpression();

  @Override
  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, params);
  }
}
