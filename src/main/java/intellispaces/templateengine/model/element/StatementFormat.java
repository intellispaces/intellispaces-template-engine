package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.model.value.Value;

import java.util.List;
import java.util.Map;

/**
 * The "Format" marker.
 */
public interface StatementFormat extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.FormatStatement;
  }

  List<String> types();

  List<TemplateElement> subStatements();

  @Override
  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, params);
  }
}
