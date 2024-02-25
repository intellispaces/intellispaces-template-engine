package intellispaces.templateengine.object.value;

import intellispaces.templateengine.exception.NotApplicableOperationException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.BooleanValue;
import intellispaces.templateengine.model.value.IntegerValue;
import intellispaces.templateengine.model.value.ListValue;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.RealValue;
import intellispaces.templateengine.model.value.StringValue;
import intellispaces.templateengine.model.value.Value;

import static intellispaces.templateengine.function.cast.CastFunctions.castToBoolean;
import static intellispaces.templateengine.function.cast.CastFunctions.castToInteger;
import static intellispaces.templateengine.function.cast.CastFunctions.castToList;
import static intellispaces.templateengine.function.cast.CastFunctions.castToMap;
import static intellispaces.templateengine.function.cast.CastFunctions.castToReal;
import static intellispaces.templateengine.function.cast.CastFunctions.castToString;

public abstract class AbstractValue implements Value {

  @Override
  public StringValue typename() {
    return StringValueBuilder.build(type().typename());
  }

  @Override
  public BooleanValue asBoolean() throws ResolveTemplateException {
    return BooleanValueBuilder.build(castToBoolean(this));
  }

  @Override
  public IntegerValue asInteger() throws ResolveTemplateException {
    return IntegerValueBuilder.build(castToInteger(this));
  }

  @Override
  public RealValue asReal() throws ResolveTemplateException {
    return RealValueBuilder.build(castToReal(this));
  }

  @Override
  public StringValue asString() throws ResolveTemplateException {
    return StringValueBuilder.build(castToString(this));
  }

  @Override
  public ListValue asList() throws ResolveTemplateException {
    return ListValueBuilder.build(castToList(this));
  }

  @Override
  public MapValue asMap() throws ResolveTemplateException {
    return MapValueBuilder.build(castToMap(this));
  }

  @Override
  public BooleanValue isVoid() {
    return BooleanValueBuilder.build(ValueTypes.Void == this.type());
  }

  @Override
  public BooleanValue isEmpty() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'isEmpty' is not applicable for value type {}. Expected string, List or map", typename().get());
  }

  @Override
  public BooleanValue isBlank() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'isBlank' is not applicable for value type {}. Expected string", typename().get());
  }

  @Override
  public StringValue capitalizeFirstLetter() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'capitalizeFirstLetter' is not applicable for value type {}. Expected string", typename().get());
  }

  @Override
  public Value invert() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'invert' is not applicable for value type {}. Expected boolean, integer or real", typename().get());
  }

  @Override
  public Value fetch(Value key) throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'fetch' is not applicable for value type {}. Expected map, list or string", typename().get());
  }

  @Override
  public Value find(Value element) throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'find' is not applicable for value type {}. Expected string or list", typename().get());
  }

  @Override
  public Value index() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'index' is not applicable for this value");
  }

  @Override
  public Value isFirst() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'isFirst' is not applicable for this value");
  }

  @Override
  public Value isLast() throws ResolveTemplateException {
    throw new NotApplicableOperationException("Operation 'isLast' is not applicable for this value");
  }
}
