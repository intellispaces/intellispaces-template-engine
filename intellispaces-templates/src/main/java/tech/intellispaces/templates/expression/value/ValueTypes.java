package tech.intellispaces.templates.expression.value;

public enum ValueTypes implements ValueType {

  Void("void"),

  Boolean("boolean"),

  Real("real"),

  Integer("integer"),

  String("string"),

  List("list"),

  Map("map");

  private final String typename;

  ValueTypes(String typename) {
    this.typename = typename;
  }

  @Override
  public String typename() {
    return typename;
  }
}
