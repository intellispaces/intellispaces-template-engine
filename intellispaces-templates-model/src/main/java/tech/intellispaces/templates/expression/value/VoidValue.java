package tech.intellispaces.templates.expression.value;

/**
 * Void value.
 */
public interface VoidValue extends Value {

  @Override
  default Value origin() {
    return this;
  }
}
