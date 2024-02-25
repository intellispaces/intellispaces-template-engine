package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.MarkerEnd;

import java.util.Objects;

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
    validate();
    return new MarkerEndObject(position);
  }

  private void validate() {
    Objects.requireNonNull(position);
  }

  private MarkerEndBuilder() {}
}
