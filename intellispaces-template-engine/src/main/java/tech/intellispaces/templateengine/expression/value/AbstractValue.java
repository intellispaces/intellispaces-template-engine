package tech.intellispaces.templateengine.expression.value;

import tech.intellispaces.templateengine.exception.NotApplicableOperationExceptions;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;

abstract class AbstractValue extends BaseValue {

  @Override
  public StringValue typename() {
    return StringValues.of(type().typename());
  }

  @Override
  public BooleanValue asBoolean() throws ResolveTemplateException {
    return BooleanValues.of(ValueFunctions.castToBoolean(this));
  }

  @Override
  public IntegerValue asInteger() throws ResolveTemplateException {
    return IntegerValues.of(ValueFunctions.castToInteger(this));
  }

  @Override
  public RealValue asReal() throws ResolveTemplateException {
    return RealValues.of(ValueFunctions.castToReal(this));
  }

  @Override
  public StringValue asString() throws ResolveTemplateException {
    return StringValues.of(ValueFunctions.castToString(this));
  }

  @Override
  public ListValue asList() throws ResolveTemplateException {
    return ListValueBuilder.build(ValueFunctions.castToList(this));
  }

  @Override
  public MapValue asMap() throws ResolveTemplateException {
    return MapValues.of(ValueFunctions.castToMap(this));
  }

  @Override
  public BooleanValue eqAnyOf(Value value1, Value value2) throws ResolveTemplateException {
    return BooleanValues.of(eq(value1).get() || eq(value2).get());
  }

  @Override
  public BooleanValue isVoid() {
    return BooleanValues.of(ValueTypes.Void == this.type());
  }

  @Override
  public BooleanValue isEmpty() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isEmpty' is not applicable for " +
        "value type {0}. Expected string, list or map", typename().get());
  }

  @Override
  public BooleanValue isNotEmpty() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isNotEmpty' is not applicable for " +
        "value type {0}. Expected string, list or map", typename().get());
  }

  @Override
  public BooleanValue isBlank() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isBlank' is not applicable for " +
        "value type {0}. Expected string", typename().get());
  }

  @Override
  public BooleanValue isNotBlank() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isNotBlank' is not applicable " +
        "for value type {0}. Expected string", typename().get());
  }

  @Override
  public StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'capitalizeFirstLetter' is not applicable for " +
        "value type {0}. Expected string", typename().get());
  }

  @Override
  public Value invert() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'invert' is not applicable for " +
        "value type {0}. Expected boolean, integer or real", typename().get());
  }

  @Override
  public Value get(Value key) throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'get' is not applicable for " +
        "value type {0}. Expected map, list or string", typename().get());
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'find' is not applicable for " +
        "value type {0}. Expected string or list", typename().get());
  }

  @Override
  public IntegerValue index() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'index' is not applicable for this value");
  }

  @Override
  public BooleanValue isFirst() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Override
  public BooleanValue isNotFirst() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isNotFirst' is not applicable for this value");
  }

  @Override
  public BooleanValue isLast() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isLast' is not applicable for this value");
  }

  @Override
  public BooleanValue isNotLast() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'isNotLast' is not applicable for this value");
  }

  @Override
  public IntegerValue size() throws ResolveTemplateException {
    throw NotApplicableOperationExceptions.withMessage("Operation 'size' is not applicable for this value");
  }
}
