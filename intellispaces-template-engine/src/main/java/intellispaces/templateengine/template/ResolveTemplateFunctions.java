package intellispaces.templateengine.template;

import intellispaces.templateengine.template.element.TemplateElement;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.expression.value.ValueFunctions;
import intellispaces.templateengine.template.expression.value.Value;

import java.util.HashMap;
import java.util.Map;

public interface ResolveTemplateFunctions {

  static String resolveTemplate(Template template, Map<String, Object> variables) throws ResolveTemplateException {
    Map<String, Value> values = new HashMap<>();
    for (Map.Entry<String, Object> entry : variables.entrySet()) {
      values.put(entry.getKey(), ValueFunctions.objectToValue(entry.getValue()));
    }

    var sb = new StringBuilder();
    for (TemplateElement element : template.elements()) {
      sb.append(element.resolve(values));
    }
    return sb.toString();
  }
}
