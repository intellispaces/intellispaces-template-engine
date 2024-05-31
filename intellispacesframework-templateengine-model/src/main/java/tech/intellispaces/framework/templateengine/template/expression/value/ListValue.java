package tech.intellispaces.framework.templateengine.template.expression.value;

import java.util.List;

/**
 * List value.
 */
public interface ListValue extends Value {

  List<Value> get();
}
