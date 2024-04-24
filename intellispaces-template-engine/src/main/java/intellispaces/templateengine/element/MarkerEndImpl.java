package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{end}}";
  }
}
