package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerWhen;
  }

  Expression condition();

  @Override
  default String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, variables);
  }
}
