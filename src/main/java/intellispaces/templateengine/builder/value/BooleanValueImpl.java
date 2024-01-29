package intellispaces.templateengine.builder.value;

import intellispaces.templateengine.model.value.BooleanValue;

final class BooleanValueImpl implements BooleanValue {
 private final boolean value;

 BooleanValueImpl(boolean value) {
  this.value = value;
 }

 @Override
 public boolean get() {
  return value;
 }
}
