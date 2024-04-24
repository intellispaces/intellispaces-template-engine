package intellispaces.templateengine.template.expression.value;

import intellispaces.templateengine.exception.NotApplicableOperationException;
import intellispaces.templateengine.exception.ResolveTemplateException;

abstract class AbstractValue implements Value {

  @Override
  public StringValue typename() {
    return StringValueBuilder.build(type().typename());
  }

  @Override
  public BooleanValue asBoolean() throws ResolveTemplateException {
    return BooleanValueBuilder.build(ValueFunctions.castToBoolean(this));
  }

  @Override
  public IntegerValue asInteger() throws ResolveTemplateException {
    return IntegerValueBuilder.build(ValueFunctions.castToInteger(this));
  }

  @Override
  public RealValue asReal() throws ResolveTemplateException {
    return RealValueBuilder.build(ValueFunctions.castToReal(this));
  }

  @Override
  public StringValue asString() throws ResolveTemplateException {
    return StringValueBuilder.build(ValueFunctions.castToString(this));
  }

  @Override
  public ListValue asList() throws ResolveTemplateException {
    return ListValueBuilder.build(ValueFunctions.castToList(this));
  }

  @Override
  public MapValue asMap() throws ResolveTemplateException {
    return MapValueBuilder.build(ValueFunctions.castToMap(this));
  }

  @Override
  public BooleanValue isVoid() {
    return BooleanValueBuilder.build(ValueTypes.Void == this.type());
  }

  @Override
  public BooleanValue isEmpty() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'isEmpty' is not applicable for value type {}. Expected string, List or map", typename().get());
  }

  @Override
  public BooleanValue isBlank() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'isBlank' is not applicable for value type {}. Expected string", typename().get());
  }

  @Override
  public StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'capitalizeFirstLetter' is not applicable for value type {}. Expected string", typename().get());
  }

  @Override
  public Value invert() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'invert' is not applicable for value type {}. Expected boolean, integer or real", typename().get());
  }

  @Override
  public Value fetch(Value key) throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'fetch' is not applicable for value type {}. Expected map, list or string", typename().get());
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'find' is not applicable for value type {}. Expected string or list", typename().get());
  }

  @Override
  public Value index() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'index' is not applicable for this value");
  }

  @Override
  public Value isFirst() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'isFirst' is not applicable for this value");
  }

  @Override
  public Value isLast() throws ResolveTemplateException {
    throw NotApplicableOperationException.withMessage("Operation 'isLast' is not applicable for this value");
  }
}
