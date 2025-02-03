package tech.intellispaces.commons.templateengine.expression.value;

import java.util.List;

/**
 * List value.
 */
public interface ListValue extends Value {

  List<Value> get();

  IntegerValue size();
}
