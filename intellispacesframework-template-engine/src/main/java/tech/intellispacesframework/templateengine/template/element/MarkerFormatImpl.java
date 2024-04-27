package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.value.Value;
import tech.intellispacesframework.templateengine.template.source.position.Position;

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
