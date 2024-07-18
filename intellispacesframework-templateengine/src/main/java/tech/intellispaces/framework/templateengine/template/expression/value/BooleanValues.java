package tech.intellispaces.framework.templateengine.template.expression.value;

public interface BooleanValues {

  static BooleanValue get(boolean value) {
    return new BooleanValueImpl(value);
  }
}
