package tech.intellispaces.framework.templateengine.template.expression;

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
