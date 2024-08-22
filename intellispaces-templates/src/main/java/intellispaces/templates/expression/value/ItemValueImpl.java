package intellispaces.templates.expression.value;

import intellispaces.templates.exception.NotApplicableOperationException;
import intellispaces.templates.exception.ResolveTemplateException;

import java.util.Objects;

class ItemValueImpl implements Value {
  private final Value element;
  private final IntegerValue index;
  private final BooleanValue first;
  private final BooleanValue last;

  ItemValueImpl(Value element, IntegerValue index, BooleanValue first, BooleanValue last) {
    this.element = element;
    this.index = index;
    this.first = first;
    this.last = last;
  }

  @Override
  public Value origin() {
    return element.origin();
  }

  @Override
  public IntegerValue index() {
    return index;
  }

  @Override
  public BooleanValue isFirst() throws ResolveTemplateException {
    if (first == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isFirst' is not applicable for this value");
    }
    return first;
  }

  @Override
  public BooleanValue isNotFirst() throws ResolveTemplateException {
    if (first == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isNotFirst' is not applicable for this value");
    }
    return first.invert();
  }

  @Override
  public BooleanValue isLast() throws ResolveTemplateException {
    if (last == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isLast' is not applicable for this value");
    }
    return last;
  }

  @Override
  public BooleanValue isNotLast() throws ResolveTemplateException {
    if (last == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isNotLast' is not applicable for this value");
    }
    return last.invert();
  }

  @Override
  public ValueType type() {
    return element.type();
  }

  @Override
  public StringValue typename() {
    return element.typename();
  }

  @Override
  public BooleanValue asBoolean() throws ResolveTemplateException {
    return element.asBoolean();
  }

  @Override
  public IntegerValue asInteger() throws ResolveTemplateException {
    return element.asInteger();
  }

  @Override
  public RealValue asReal() throws ResolveTemplateException {
    return element.asReal();
  }

  @Override
  public StringValue asString() throws ResolveTemplateException {
    return element.asString();
  }

  @Override
  public ListValue asList() throws ResolveTemplateException {
    return element.asList();
  }

  @Override
  public MapValue asMap() throws ResolveTemplateException {
    return element.asMap();
  }

  @Override
  public BooleanValue eq(Value other) throws ResolveTemplateException {
    return element.eq(other);
  }

  @Override
  public BooleanValue isVoid() {
    return element.isVoid();
  }

  @Override
  public BooleanValue isEmpty() throws ResolveTemplateException {
    return element.isEmpty();
  }

  @Override
  public BooleanValue isNotEmpty() throws ResolveTemplateException {
    return element.isNotEmpty();
  }

  @Override
  public BooleanValue isBlank() throws ResolveTemplateException {
    return element.isBlank();
  }

  @Override
  public BooleanValue isNotBlank() throws ResolveTemplateException {
    return element.isNotBlank();
  }

  @Override
  public StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    return element.capitalizeFirstLetter();
  }

  @Override
  public Value invert() throws ResolveTemplateException {
    return element.invert();
  }

  @Override
  public Value get(Value key) throws ResolveTemplateException {
    return element.get(key);
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    return this.element.find(element);
  }

  @Override
  public IntegerValue size() throws ResolveTemplateException {
    return this.element.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!Value.class.isInstance(o)) {
      return false;
    }
    Value that = (Value) o;
    return element.origin().equals(that.origin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(element);
  }
}
