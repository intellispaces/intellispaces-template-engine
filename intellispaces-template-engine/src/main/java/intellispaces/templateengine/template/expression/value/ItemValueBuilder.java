package intellispaces.templateengine.template.expression.value;

import java.util.List;
import java.util.Objects;

public final class ItemValueBuilder {
  private Value value;
  private Value index;
  private Value first;
  private Value last;

  public static ItemValueBuilder get() {
    return new ItemValueBuilder();
  }

  public ItemValueBuilder value(Value value) {
    this.value = value;
    return this;
  }

  public ItemValueBuilder value(boolean value) {
    this.value = BooleanValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder value(int value) {
    this.value = IntegerValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder value(double value) {
    this.value = RealValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder value(String value) {
    this.value = StringValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder value(List<Value> value) {
    this.value = ListValueBuilder.build(value);
    return this;
  }

  public ItemValueBuilder index(Value index) {
    this.index = index;
    return this;
  }

  public ItemValueBuilder index(int index) {
    this.index = IntegerValueBuilder.build(index);
    return this;
  }

  public ItemValueBuilder first(Value first) {
    this.first = first;
    return this;
  }

  public ItemValueBuilder first(boolean first) {
    this.first = BooleanValueBuilder.build(first);
    return this;
  }

  public ItemValueBuilder last(Value last) {
    this.last = last;
    return this;
  }

  public ItemValueBuilder last(boolean last) {
    this.last = BooleanValueBuilder.build(last);
    return this;
  }

  public Value build() {
    validate();
    return new ItemValueImpl(value, index, first, last);
  }

  private void validate() {
    Objects.requireNonNull(value);
    Objects.requireNonNull(index);
  }

  private ItemValueBuilder() {}
}
