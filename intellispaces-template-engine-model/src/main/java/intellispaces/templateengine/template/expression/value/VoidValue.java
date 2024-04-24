package intellispaces.templateengine.template.expression.value;

/**
 * Void value.
 */
public interface VoidValue extends Value {

  @Override
  default Value origin() {
    return this;
  }
}
