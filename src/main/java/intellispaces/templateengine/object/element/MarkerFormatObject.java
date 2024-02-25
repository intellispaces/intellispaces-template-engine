package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.MarkerFormatType;

import java.util.List;

final class MarkerFormatObject implements MarkerFormat {
  private final TextPosition position;
  private final List<MarkerFormatType> types;

  MarkerFormatObject(TextPosition position, List<MarkerFormatType> types) {
    this.position = position;
    this.types = types;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public List<MarkerFormatType> types() {
    return types;
  }
}
