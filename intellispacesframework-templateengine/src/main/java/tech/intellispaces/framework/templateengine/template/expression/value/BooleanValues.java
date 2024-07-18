package tech.intellispaces.framework.templateengine.template.expression.value;

public interface BooleanValues {

  static BooleanValue of(boolean value) {
    return new BooleanValueImpl(value);
  }
}
