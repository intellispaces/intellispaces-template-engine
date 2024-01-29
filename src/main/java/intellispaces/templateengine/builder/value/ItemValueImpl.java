package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.ItemValue;
import intellispaces.templateengine.model.value.Value;

final class ItemValueImpl implements ItemValue {
 private final Value value;
 private final IntegerValue index;
 private final BooleanValue isFirst;
 private final BooleanValue isLast;

 ItemValueImpl(Value value, IntegerValue index, BooleanValue isFirst, BooleanValue isLast) {
  this.value = value;
  this.index = index;
  this.isFirst = isFirst;
  this.isLast = isLast;
 }

 @Override
 public Value get() {
  return value;
 }

 @Override
 public IntegerValue index() {
  return index;
 }

 public BooleanValue isFirst() {
  return isFirst;
 }

 @Override
 public BooleanValue isLast() {
  return isLast;
 }
}
