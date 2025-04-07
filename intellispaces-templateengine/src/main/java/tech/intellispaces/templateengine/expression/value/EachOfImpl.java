package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.value.api.EachOfApi;

import java.util.List;

class EachOfImpl implements EachOfApi {
  private final List<Value> values;

  EachOfImpl(List<Value> values) {
    this.values = values;
  }

  @Override
  public BooleanValue isTrue() throws ResolveTemplateException {
    for (Value value : values) {
      if (!value.asBoolean().get()) {
        return BooleanValues.of(false);
      }
    }
    return BooleanValues.of(true);
  }
}
