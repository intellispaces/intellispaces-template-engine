package intellispaces.templateengine.model.element;

import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * The "Else" marker element.
 */
public interface MarkerElse extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.MarkerElse;
  }

  @Override
  default String text() {
    return MarkerTypes.Else.text();
  }

  @Override
  default String resolve(Map<String, Value> params) {
    return ResolveFunctions.resolve(this, params);
  }
}
