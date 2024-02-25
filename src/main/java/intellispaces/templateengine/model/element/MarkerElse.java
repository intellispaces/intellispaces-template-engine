package intellispaces.templateengine.model.element;

import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerElse;
  }

  Expression condition();

  @Override
  default String resolve(Map<String, Value> variables) {
    return ResolveFunctions.resolve(this, variables);
  }
}
