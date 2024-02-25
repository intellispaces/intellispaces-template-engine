package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

/**
 * Text template element.
 */
public interface TextElement extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.Text;
  }

  String text();

  @Override
  default String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, variables);
  }
}
