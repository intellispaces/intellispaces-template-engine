package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.MarkerFormatType;

import java.util.List;
import java.util.Objects;

public final class MarkerFormatBuilder {
  private TextPosition position;
  private List<MarkerFormatType> types;

  public static MarkerFormatBuilder get() {
    return new MarkerFormatBuilder();
  }

  public MarkerFormatBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerFormatBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public MarkerFormat build() {
    validate();
    return new MarkerFormatObject(position, types);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(types);
  }

  private MarkerFormatBuilder() {}
}
