package tech.intellispaces.commons.templateengine.expression.compilation;

import tech.intellispaces.commons.templateengine.expression.value.EachOfs;
import tech.intellispaces.commons.templateengine.expression.value.Value;
import tech.intellispaces.commons.templateengine.expression.value.api.EachOfApi;
import tech.intellispaces.commons.templateengine.expression.value.api.ExpressionApi;
import tech.intellispaces.commons.templateengine.expression.value.api.ValueApi;

public interface ExpressionApiImpl extends ExpressionApi {

  @Override
  default EachOfApi eachOf(ValueApi value1, ValueApi value2) {
    return EachOfs.get((Value) value1, (Value) value2);
  }
}
