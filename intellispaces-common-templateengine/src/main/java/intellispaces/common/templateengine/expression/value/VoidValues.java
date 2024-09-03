package intellispaces.common.templateengine.expression.value;

public enum VoidValues {

  Void(new VoidValueImpl());

  public static VoidValue get() {
    return Void.instance;
  }

  private final VoidValue instance;

  VoidValues(VoidValue instance) {
    this.instance = instance;
  }
}
