package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.templateengine.expression.value.api.EachOfApi;

import java.util.List;

public interface EachOfs {

  static EachOfApi get(Value value1, Value value2) {
    return new EachOfImpl(List.of(value1, value2));
  }
}
