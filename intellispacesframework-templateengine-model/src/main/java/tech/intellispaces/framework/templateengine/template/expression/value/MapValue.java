package tech.intellispaces.framework.templateengine.template.expression.value;

import java.util.Map;

/**
 * Map value.
 */
public interface MapValue extends Value {

  Map<Value, Value> get();
}
