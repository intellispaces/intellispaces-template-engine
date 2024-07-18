package tech.intellispaces.framework.templateengine.template.expression;

import java.util.Objects;

public final class VariableBuilder {
  private String name;

  VariableBuilder() {}

  public VariableBuilder name(String name) {
    this.name = name;
    return this;
  }

  public Variable get() {
    validate();
    return new VariableImpl(name);
  }

  private void validate() {
    Objects.requireNonNull(name);
    if (name.trim().isEmpty()) {
      throw new RuntimeException("Invalid variable name: " + name);
    }
  }
}
