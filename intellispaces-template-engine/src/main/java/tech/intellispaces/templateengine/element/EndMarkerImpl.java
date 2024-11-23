package tech.intellispaces.templateengine.element;

import tech.intellispaces.templateengine.expression.value.Value;

import java.util.Map;

class EndMarkerImpl extends AbstractElement implements MarkerEnd {

  EndMarkerImpl(TemplateElementContext context) {
    super(context);
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerEnd;
  }

  @Override
  public String resolve(Map<String, Value> variables) {
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{end}}";
  }
}
