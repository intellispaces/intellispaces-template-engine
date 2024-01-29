package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.ListValue;

import java.util.List;

final class ListValueImpl implements ListValue {
 private final List<?> list;

 ListValueImpl(List<?> list) {
  this.list = list;
 }

 @Override
 public List<?> get() {
  return list;
 }
}
