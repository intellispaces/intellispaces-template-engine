package tech.intellispaces.templates.template.expression.value;

import java.util.List;
import java.util.Objects;

public final class ItemValueBuilder {
  private Value value;
  private IntegerValue index;
  private BooleanValue first;
  private BooleanValue last;

  ItemValueBuilder() {}

  public ItemValueBuilder value(Value value) {
    this.value = value;
    return this;
  }

  public ItemValueBuilder value(boolean value) {
    this.value = BooleanValues.of(value);
    return this;
  }

  public ItemValueBuilder value(int value) {
    this.value = IntegerValues.of(value);
    return this;
  }

  public ItemValueBuilder value(double value) {
    this.value = RealValues.of(value);
    return this;
  }

  public ItemValueBuilder value(String value) {
    this.value = StringValues.of(value);
    return this;
  }

  public ItemValueBuilder value(List<Value> value) {
    this.value = ListValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder index(IntegerValue index) {
    this.index = index;
    return this;
  }

  public ItemValueBuilder index(int index) {
    this.index = IntegerValues.of(index);
    return this;
  }

  public ItemValueBuilder first(BooleanValue first) {
    this.first = first;
    return this;
  }

  public ItemValueBuilder first(boolean first) {
    this.first = BooleanValues.of(first);
    return this;
  }

  public ItemValueBuilder last(BooleanValue last) {
    this.last = last;
    return this;
  }

  public ItemValueBuilder last(boolean last) {
    this.last = BooleanValues.of(last);
    return this;
  }

  public Value get() {
    validate();
    return new ItemValueImpl(value, index, first, last);
  }

  private void validate() {
    Objects.requireNonNull(value);
    Objects.requireNonNull(index);
  }
}
