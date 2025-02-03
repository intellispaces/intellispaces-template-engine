package tech.intellispaces.commons.templateengine.expression.value;

/**
 * Void value.
 */
public interface VoidValue extends Value {

  @Override
  default Value origin() {
    return this;
  }
}
