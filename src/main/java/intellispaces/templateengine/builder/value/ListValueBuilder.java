package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.ListValue;

import java.util.List;

public final class ListValueBuilder {

  public static ListValue build(List<?> list) {
    return new ListValueImpl(list);
  }
}
