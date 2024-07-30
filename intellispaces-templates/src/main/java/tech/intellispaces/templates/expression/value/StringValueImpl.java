package tech.intellispaces.templates.expression.value;

import tech.intellispaces.commons.string.StringFunctions;
import tech.intellispaces.templates.exception.IrregularValueTypeException;
import tech.intellispaces.templates.exception.ResolveTemplateException;

import java.util.Objects;

class StringValueImpl extends AbstractValue implements StringValue {
  private final String value;

  StringValueImpl(String value) {
    this.value = value;
  }

  @Override
  public ValueType type() {
    return ValueTypes.String;
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
      return BooleanValues.of(Objects.equals(get(), ((StringValue) other).get()));
    }
    return BooleanValues.of(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValues.of(get() == null || get().isEmpty());
  }

  @Override
  public BooleanValue isNotEmpty() throws ResolveTemplateException {
    return BooleanValues.of(get() != null && !get().isEmpty());
  }

  @Override
  public BooleanValue isBlank() {
    return BooleanValues.of(get() == null || get().isBlank());
  }

  @Override
  public BooleanValue isNotBlank() throws ResolveTemplateException {
    return BooleanValues.of(get() != null && !get().isBlank());
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
      throw IrregularValueTypeException.withMessage("Invalid argument value type: {}. Expected string, integer, real or boolean", typename().get());
    }

    int index = get().indexOf(subString);
    if (index < 0) {
      return VoidValues.get();
    }
    return ItemValues.build()
        .value(subString)
        .index(index)
        .get();
  }

  @Override
  public Value get(Value key) throws ResolveTemplateException {
    if (key.type() != ValueTypes.Integer) {
      throw IrregularValueTypeException.withMessage("Invalid index type: {}. Expected integer value", key.typename().get());
    }
    int index = ((IntegerValue) key).get();
    if (index < 0 || index >= get().length()) {
      return ItemValues.build()
          .value(VoidValues.get())
          .index((IntegerValue) key)
          .get();
    }
    return ItemValues.build()
        .value("" + get().charAt(index))
        .index((IntegerValue) key)
        .first(index == 0)
        .last(index == get().length() - 1)
        .get();
  }

  @Override
  public StringValue capitalizeFirstLetter() {
    return StringValues.of(StringFunctions.capitalizeFirstLetter(get()));
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
