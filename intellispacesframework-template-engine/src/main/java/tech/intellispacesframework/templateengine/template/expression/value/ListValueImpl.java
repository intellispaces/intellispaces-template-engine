package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.templateengine.exception.IrregularValueTypeException;
import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;

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
      return BooleanValueBuilder.build(Objects.equals(get(), ((ListValue) other).get()));
    }
    return BooleanValueBuilder.build(false);
  }

  @Override
  public BooleanValue isEmpty() {
    return BooleanValueBuilder.build(get().isEmpty());
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
    return ItemValueBuilder.get()
        .value(get().subList(index, index + subList.size()))
        .index(index)
        .build();
  }

  @Override
  public Value fetch(Value key) throws ResolveTemplateException {
    if (key.type() != ValueTypes.Integer) {
      throw IrregularValueTypeException.withMessage("Invalid index type: {}. Expected integer", key.typename().get());
    }
    int index = ((IntegerValue) key).get();
    if (index < 0 || index >= get().size()) {
      return ItemValueBuilder.get()
          .value(VoidValues.get())
          .index(key)
          .build();
    }

    Value element = get().get(index);
    return ItemValueBuilder.get()
        .value(element)
        .index(key)
        .first(index == 0)
        .last(index == get().size() - 1)
        .build();
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
