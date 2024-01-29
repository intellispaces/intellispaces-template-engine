package intellispaces.templateengine.model.value;

public enum ValueTypes implements ValueType {

  Null("null"),

  Boolean("boolean"),

  Double("double"),

  Integer("integer"),

  String("string"),

  List("list"),

  Map("map"),

  Item("item");

  private final String typename;

  ValueTypes(String typename) {
    this.typename = typename;
  }

  @Override
  public String typename() {
    return typename;
  }
}
