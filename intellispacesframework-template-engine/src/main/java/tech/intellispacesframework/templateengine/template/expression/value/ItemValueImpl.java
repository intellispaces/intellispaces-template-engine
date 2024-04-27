package tech.intellispacesframework.templateengine.template.expression.value;

import tech.intellispacesframework.templateengine.exception.NotApplicableOperationException;
import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;

import java.util.Objects;

class ItemValueImpl implements Value {
  private final Value element;
  private final Value index;
  private final Value first;
  private final Value last;

  ItemValueImpl(Value element, Value index, Value first, Value last) {
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
  public Value index() {
    return index;
  }

  @Override
  public Value isFirst() throws ResolveTemplateException {
    if (first == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isFirst' is not applicable for this value");
    }
    return first;
  }

  @Override
  public Value isLast() throws ResolveTemplateException {
    if (last == null) {
      throw NotApplicableOperationException.withMessage("Operation 'isLast' is not applicable for this value");
    }
    return last;
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
  public BooleanValue isBlank() throws ResolveTemplateException {
    return element.isBlank();
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
  public Value fetch(Value key) throws ResolveTemplateException {
    return element.fetch(key);
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    return this.element.find(element);
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
