package tech.intellispaces.framework.templateengine.template.expression.value;

import tech.intellispaces.framework.commons.collection.ArraysFunctions;
import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;
import tech.intellispaces.framework.commons.function.Functions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class ListValueBuilder {
  private List<Value> list;

  ListValueBuilder() {}

  static ListValue build(List<Value> list) {
    validate(list);
    return new ListValueImpl(list);
  }

  static ListValue build(Object[] elements) {
    if (elements == null) {
      return build(List.of());
    }
    return build(toValueList(elements));
  }

  private static List<Value> toValueList(Object[] elements) {
    return Arrays.stream(elements)
        .map(Functions.coveredThrowableFunction(ValueFunctions::objectToValue))
        .toList();
  }

  public ListValueBuilder value(boolean... elements) {
    this.list = toValueList(ArraysFunctions.wrap(elements));
    return this;
  }

  public ListValueBuilder value(int... elements) {
    this.list = toValueList(ArraysFunctions.wrap(elements));
    return this;
  }

  public ListValueBuilder value(double... elements) {
    this.list = toValueList(ArraysFunctions.wrap(elements));
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

  public ListValue get() {
    validate(list);
    return new ListValueImpl(list);
  }

  private static void validate(List<? extends Value> list) {
    Objects.requireNonNull(list);
    if (list.stream().anyMatch(v -> v.isVoid().get())) {
      throw UnexpectedViolationException.withMessage("List can't contain void value");
    }
  }
}
