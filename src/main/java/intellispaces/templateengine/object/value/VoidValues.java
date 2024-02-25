package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.VoidValue;

public enum VoidValues {

  Void(new VoidValueObject());

  public static VoidValue get() {
    return Void.instance;
  }

  private final VoidValue instance;

  VoidValues(VoidValue instance) {
    this.instance = instance;
  }
}
