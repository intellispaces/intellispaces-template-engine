package tech.intellispaces.commons.templateengine.expression.value;

import tech.intellispaces.commons.collection.ArraysFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public final class ListValues {

  private ListValues() {}

  public static ListValueBuilder build() {
    return new ListValueBuilder();
  }

  public static ListValue of(List<Value> list) {
    return ListValueBuilder.build(list);
  }

  public static ListValue of(Object[] elements) {
    return ListValueBuilder.build(elements);
  }

  public static ListValue of(boolean... elements) {
    return ListValueBuilder.build(ArraysFunctions.wrap(elements));
  }

  public static ListValue of(int... elements) {
    return ListValueBuilder.build(IntStream.of(elements).boxed().toArray());
  }

  public static ListValue of(double... elements) {
    return ListValueBuilder.build(DoubleStream.of(elements).boxed().toArray());
  }

  public static ListValue of(String... elements) {
    return ListValueBuilder.build(elements);
  }

  public static ListValue of(Value... elements) {
    if (elements == null) {
      return ListValueBuilder.build(List.of());
    }
    return ListValueBuilder.build(Arrays.stream(elements).toList());
  }

  public static ListValue empty() {
    return EMPTY;
  }

  private static final ListValue EMPTY = ListValueBuilder.build(List.of());
}
