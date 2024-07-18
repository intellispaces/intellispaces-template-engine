package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.List;
import java.util.Map;

class FormatMarkerImpl extends AbstractElement implements MarkerFormat {
  private final List<MarkerFormatType> types;

  FormatMarkerImpl(TemplateElementContext context, List<MarkerFormatType> types) {
    super(context);
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
