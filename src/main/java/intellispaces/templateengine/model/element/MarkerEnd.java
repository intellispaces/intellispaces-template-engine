package intellispaces.templateengine.model.element;

import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * Marker <end>.
 */
public interface MarkerEnd extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerEnd;
  }

  @Override
  default String resolve(Map<String, Value> variables) {
    return ResolveFunctions.resolve(this, variables);
  }
}
