package intellispaces.templateengine.object.value;

import intellispaces.templateengine.model.value.RealValue;

public interface RealValueBuilder {

  static RealValue build(double value) {
    return new RealValueObject(value);
  }
}
