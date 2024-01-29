package intellispaces.templateengine.model.element;

import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * The "End" marker element.
 */
public interface MarkerEnd extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerEnd;
  }

  @Override
  default String text() {
    return MarkerTypes.End.text();
  }

  @Override
  default String resolve(Map<String, Value> params) {
    return ResolveFunctions.resolve(this, params);
  }
}
