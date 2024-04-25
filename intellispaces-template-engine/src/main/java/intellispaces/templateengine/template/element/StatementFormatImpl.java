package intellispaces.templateengine.template.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Map;

class StatementFormatImpl extends AbstractElement implements StatementFormat {
  private final List<MarkerFormatType> types;
  private final List<TemplateElement> subElements;

  StatementFormatImpl(Position position, List<MarkerFormatType> types, List<TemplateElement> subElements) {
    super(position);
    this.types = types;
    this.subElements = subElements;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.StatementFormat;
  }

  @Override
  public List<MarkerFormatType> types() {
    return types;
  }

  @Override
  public List<TemplateElement> subElements() {
    return subElements;
  }

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ElementFunctions.resolve(this, variables);
  }
}
