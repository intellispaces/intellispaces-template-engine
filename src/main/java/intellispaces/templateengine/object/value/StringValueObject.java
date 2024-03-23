package intellispaces.templateengine.object.value;

import intellispaces.commons.function.StringFunctions;
import intellispaces.templateengine.exception.IrregularValueTypeException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.RealValue;
import intellispaces.templateengine.model.value.StringValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

final class StringValueObject extends AbstractValue implements StringValue {
  private final String value;

  StringValueObject(String value) {
    this.value = value;
  }

  public String get() {
    return value;
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public StringValue asString() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.String) {
      return BooleanValueBuilder.build(Objects.equals(get(), ((StringValue) other).get()));
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get() == null || get().isEmpty());
  }

  @Override
  public BooleanValue isBlank() {
    return BooleanValueBuilder.build(get() == null || get().isBlank());
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    final String subString;
    if (element.type() == ValueTypes.String) {
      subString = ((StringValue) element).get();
    } else if (element.type() == ValueTypes.Integer) {
      subString = (Integer.toString(((IntegerValue) element).get()));
    } else if (element.type() == ValueTypes.Real) {
      subString = (Double.toString(((RealValue) element).get()));
    } else if (element.type() == ValueTypes.Boolean) {
      subString = (Boolean.toString(((BooleanValue) element).get()));
    } else {
      throw new IrregularValueTypeException("Invalid argument value type: {}. Expected string, integer, real or boolean", typename().get());
    }

    int index = get().indexOf(subString);
    if (index < 0) {
      return VoidValues.get();
    }
    return ItemValueBuilder.get()
        .value(subString)
        .index(index)
        .build();
  }

  @Override
  public Value fetch(Value key) throws ResolveTemplateException {
    if (key.type() != ValueTypes.Integer) {
      throw new IrregularValueTypeException("Invalid index type: {}. Expected integer", key.typename().get());
    }
    int index = ((IntegerValue) key).get();
    if (index < 0 || index >= get().length()) {
      return ItemValueBuilder.get()
          .value(VoidValues.get())
          .index(key)
          .build();
    }
    return ItemValueBuilder.get()
        .value("" + get().charAt(index))
        .index(key)
        .first(index == 0)
        .last(index == get().length() - 1)
        .build();
  }

  @Override
  public StringValue capitalizeFirstLetter() {
    return StringValueBuilder.build(StringFunctions.capitalizeFirstLetter(get()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!StringValue.class.isInstance(o)) {
      return false;
    }
    StringValue other = (StringValue) o;
    return Objects.equals(get(), other.get());
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
