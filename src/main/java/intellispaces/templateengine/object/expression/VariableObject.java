package intellispaces.templateengine.object.expression;

import intellispaces.templateengine.model.expression.Variable;

final class VariableObject implements Variable {
  private final String name;

  VariableObject(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }
}
