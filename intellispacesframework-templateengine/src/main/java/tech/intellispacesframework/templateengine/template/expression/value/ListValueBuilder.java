package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static tech.intellispacesframework.commons.exception.ExceptionFunctions.coverThrowableFunction;

public final class ListValueBuilder {
  private List<Value> list;

  public static ListValue build(boolean... elements) {
    Boolean[] booleans = asBooleanArray(elements);
    return buildFromArray(booleans);
  }

  public static ListValue build(int... elements) {
    return buildFromArray(IntStream.of(elements).boxed().toArray());
  }

  public static ListValue build(double... elements) {
    return buildFromArray(DoubleStream.of(elements).boxed().toArray());
  }

  public static ListValue build(String... elements) {
    return buildFromArray(elements);
  }

  public static ListValue build(Value... elements) {
    if (elements == null) {
      return build(List.of());
    }
    return build(Arrays.stream(elements).toList());
  }

  public static ListValue empty() {
    return build(List.of());
  }

  public static ListValue build(List<Value> list) {
    validate(list);
    return new ListValueImpl(list);
  }

  private static ListValue buildFromArray(Object[] elements) {
    if (elements == null) {
      return build(List.of());
    }
    return build(toValueList(elements));
  }

  private static List<Value> toValueList(Object[] elements) {
    return Arrays.stream(elements)
        .map(coverThrowableFunction(ValueFunctions::objectToValue))
        .toList();
  }

  public static ListValueBuilder get() {
    return new ListValueBuilder();
  }

  public ListValueBuilder value(boolean... elements) {
    Boolean[] booleans = asBooleanArray(elements);
    this.list = toValueList(booleans);
    return this;
  }

  public ListValueBuilder value(int... elements) {
    this.list = toValueList(IntStream.of(elements).boxed().toArray());
    return this;
  }

  public ListValueBuilder value(double... elements) {
    this.list = toValueList(DoubleStream.of(elements).boxed().toArray());
    return this;
  }

  public ListValueBuilder value(String... elements) {
    this.list = toValueList(elements);
    return this;
  }

  public ListValueBuilder value(List<Value> elements) {
    this.list = elements;
    return this;
  }

  public ListValue build() {
    validate(list);
    return new ListValueImpl(list);
  }

  private static void validate(List<? extends Value> list) {
    Objects.requireNonNull(list);
    if (list.stream().anyMatch(v -> v.isVoid().get())) {
      throw UnexpectedViolationException.withMessage("List can't contain void value");
    }
  }

  private static Boolean[] asBooleanArray(boolean[] elements) {
    return IntStream.range(0, elements.length).mapToObj(ind -> elements[ind]).toArray(Boolean[]::new);
  }

  private ListValueBuilder() {}
}
