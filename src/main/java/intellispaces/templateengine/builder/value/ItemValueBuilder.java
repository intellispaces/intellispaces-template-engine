package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.ItemValue;
import intellispaces.templateengine.model.value.Value;

import java.util.Objects;

public final class ItemValueBuilder {
  private Value value;
  private IntegerValue index;
  private BooleanValue isFirst;
  private BooleanValue isLast;

  public static ItemValueBuilder get() {
    return new ItemValueBuilder();
  }

  public ItemValueBuilder value(Value value) {
    this.value = value;
    return this;
  }

  public ItemValueBuilder index(IntegerValue index) {
    this.index = index;
    return this;
  }

  public ItemValueBuilder isFirst(BooleanValue isFirst) {
    this.isFirst = isFirst;
    return this;
  }

  public ItemValueBuilder isLast(BooleanValue isLast) {
    this.isLast = isLast;
    return this;
  }

  public ItemValue build() {
    Objects.requireNonNull(value);
    Objects.requireNonNull(index);
    Objects.requireNonNull(isFirst);
    Objects.requireNonNull(isLast);
    return new ItemValueImpl(value, index, isFirst, isLast);
  }
}
