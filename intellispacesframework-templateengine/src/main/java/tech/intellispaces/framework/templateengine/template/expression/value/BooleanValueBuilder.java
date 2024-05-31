package tech.intellispaces.framework.templateengine.template.expression.value;

public interface BooleanValueBuilder {

  static BooleanValue build(boolean value) {
    return new BooleanValueImpl(value);
  }
}
