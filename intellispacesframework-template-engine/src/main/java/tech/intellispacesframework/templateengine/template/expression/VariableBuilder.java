package tech.intellispacesframework.templateengine.template.expression;

import java.util.Objects;

public final class VariableBuilder {
  private String name;

  public static VariableBuilder get() {
    return new VariableBuilder();
  }

  public VariableBuilder name(String name) {
    this.name = name;
    return this;
  }

  public Variable build() {
    validate();
    return new VariableImpl(name);
  }

  private void validate() {
    Objects.requireNonNull(name);
    if (name.trim().isEmpty()) {
      throw new RuntimeException("Invalid variable name: " + name);
    }
  }

  private VariableBuilder() {}
}
