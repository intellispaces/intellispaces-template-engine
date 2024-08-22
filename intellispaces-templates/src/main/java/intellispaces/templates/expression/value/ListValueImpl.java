package intellispaces.templates.expression.value;

import intellispaces.templates.exception.IrregularValueTypeException;
import intellispaces.templates.exception.ResolveTemplateException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

class ListValueImpl extends AbstractValue implements ListValue {
  private final List<Value> list;

  ListValueImpl(List<Value> list) {
    this.list = list;
  }

  @Override
  public ValueType type() {
    return ValueTypes.List;
  }

  @Override
  public List<Value> get() {
    return list;
  }

  @Override
  public IntegerValue size() {
    return IntegerValues.of(list.size());
  }

  @Override
  public Value origin() {
    return this;
  }

  @Override
  public ListValue asList() {
    return this;
  }

  @Override
  public BooleanValue eq(Value other) {
    if (other.type() == ValueTypes.List) {
      return BooleanValues.of(Objects.equals(get(), ((ListValue) other).get()));
    }
    return BooleanValues.of(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValues.of(get().isEmpty());
  }

  @Override
  public BooleanValue isNotEmpty() {
    return BooleanValues.of(!get().isEmpty());
  }

  @Override
  public Value find(Value element) {
    final List<Value> subList;
    if (element.type() == ValueTypes.List) {
      subList = ((ListValue) element).get();
    } else {
      subList = List.of(element);
    }
    Objects.requireNonNull(subList);

    int index = Collections.indexOfSubList(get(), subList);
    if (index < 0) {
      return VoidValues.get();
    }
    return ItemValues.build()
        .value(get().subList(index, index + subList.size()))
        .index(index)
        .get();
  }

  @Override
  public Value get(Value key) throws ResolveTemplateException {
    if (key.type() != ValueTypes.Integer) {
      throw IrregularValueTypeException.withMessage("Invalid index type: {}. Expected integer value", key.typename().get());
    }
    int index = ((IntegerValue) key).get();
    if (index < 0 || index >= get().size()) {
      return ItemValues.build()
          .value(VoidValues.get())
          .index((IntegerValue) key)
          .get();
    }

    Value element = get().get(index);
    return ItemValues.build()
        .value(element)
        .index((IntegerValue) key)
        .first(index == 0)
        .last(index == get().size() - 1)
        .get();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!ListValue.class.isInstance(o)) {
      return false;
    }
    ListValue other = (ListValue) o;
    return get().equals(other.get());
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }
}
