package intellispaces.templateengine.template.element;

import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Map;

class MarkerFormatImpl extends AbstractElement implements MarkerFormat {
  private final List<MarkerFormatType> types;

  MarkerFormatImpl(Position position, List<MarkerFormatType> types) {
    super(position);
    this.types = types;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerFormat;
  }

  @Override
  public List<MarkerFormatType> types() {
    return types;
  }

  @Override
  public String resolve(Map<String, Value> variables) {
    return ElementFunctions.resolve(this, variables);
  }
}
