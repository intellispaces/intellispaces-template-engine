package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerFormatType;
import intellispaces.templateengine.model.element.StatementFormat;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.List;

final class StatementFormatObject implements StatementFormat {
  private final TextPosition position;
  private final List<MarkerFormatType> types;
  private final List<TemplateElement> subElements;

  StatementFormatObject(TextPosition position, List<MarkerFormatType> types, List<TemplateElement> subElements) {
    this.position = position;
    this.types = types;
    this.subElements = subElements;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public List<MarkerFormatType> types() {
    return types;
  }

  @Override
  public List<TemplateElement> subElements() {
    return subElements;
  }
}
