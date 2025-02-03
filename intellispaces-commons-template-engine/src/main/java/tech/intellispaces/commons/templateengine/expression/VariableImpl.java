package tech.intellispaces.commons.templateengine.expression;

class VariableImpl implements Variable {
  private final String name;

  VariableImpl(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }
}
