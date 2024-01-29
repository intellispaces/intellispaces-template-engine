package intellispaces.templateengine.builder.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerEnd;

public final class MarkerEndBuilder {
  private TextPosition position;

  public static MarkerEndBuilder get() {
    return new MarkerEndBuilder();
  }

  public MarkerEndBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public MarkerEnd build() {
    return new MarkerEndImpl(position);
  }
}
