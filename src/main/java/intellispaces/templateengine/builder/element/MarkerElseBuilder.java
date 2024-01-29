package intellispaces.templateengine.builder.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerElse;

public final class MarkerElseBuilder {
  private TextPosition position;

  public static MarkerElseBuilder get() {
    return new MarkerElseBuilder();
  }

  public MarkerElseBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerElse build() {
    return new MarkerElseImpl(position);
  }
}
