package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

import java.util.Map;

class MarkerEndImpl extends AbstractElement implements MarkerEnd {

  MarkerEndImpl(Position position) {
    super(position);
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
