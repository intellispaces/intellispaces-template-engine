package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.expression.value.api.EachOfApi;

import java.util.List;

public interface EachOfs {

  static EachOfApi get(Value value1, Value value2) {
    return new EachOfImpl(List.of(value1, value2));
  }
}
