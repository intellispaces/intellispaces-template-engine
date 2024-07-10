package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class MarkerEndImpl extends AbstractElement implements MarkerEnd {

  MarkerEndImpl(TemplateElementContext context) {
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
