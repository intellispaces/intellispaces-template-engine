package intellispaces.templateengine.model.value;

import intellispaces.templateengine.builder.value.BooleanValueBuilder;
import intellispaces.templateengine.builder.value.ItemValueBuilder;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.CastFunctions;

import java.util.List;

public interface ListValue extends Value {

  List<?> get();

  @Override
  default ValueType type() {
    return ValueTypes.List;
  }

  @Override
  default BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get().isEmpty());
  }

  @Override
  default Value at(Value indexValue) throws ResolveTemplateException {
    if (ValueTypes.Integer != indexValue.type()) {
      throw new ResolveTemplateException("Unexpected argument value type: {}. Expected integer value", indexValue.typename().get());
    }

    var index = ((IntegerValue) indexValue).get();
    Object value = get().get(index);
    return ItemValueBuilder.get()
        .value(CastFunctions.castToValue(value))
        .index((IntegerValue) indexValue)
        .isFirst(BooleanValueBuilder.build(index == 0))
        .isLast(BooleanValueBuilder.build(index >= get().size()))
        .build();
  }
}
