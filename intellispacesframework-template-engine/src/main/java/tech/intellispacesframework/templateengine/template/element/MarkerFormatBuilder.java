package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Objects;

public final class MarkerFormatBuilder {
  private Position position;
  private List<MarkerFormatType> types;

  public static MarkerFormatBuilder get() {
    return new MarkerFormatBuilder();
  }

  public MarkerFormatBuilder position(Position position) {
    this.position = position;
    return this;
  }

  public MarkerFormatBuilder types(List<MarkerFormatType> types) {
    this.types = types;
    return this;
  }

  public MarkerFormat build() {
    validate();
    return new MarkerFormatImpl(position, types);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(types);
  }

  private MarkerFormatBuilder() {}
}
