package intellispaces.templateengine.function.resolve;

import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.cast.CastFunctions;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.expression.Operand;
import intellispaces.templateengine.model.value.ListValue;
import intellispaces.templateengine.model.value.MapValue;
import intellispaces.templateengine.model.value.StringValue;
import intellispaces.templateengine.model.value.Value;
import intellispaces.templateengine.object.value.ValueTypes;

import java.util.List;
import java.util.Map;

public interface ResolveExpressionFunctions {

  static String resolveExpressionToString(Expression expression, Map<String, Value> variables) throws ResolveTemplateException {
    Value value = resolveExpression(expression, variables);
    return convertToString(value);
  }

  static boolean resolveExpressionToBoolean(Expression expression, Map<String, Value> variables) throws ResolveTemplateException {
    Value value = resolveExpression(expression, variables);
    return CastFunctions.castToBoolean(value);
  }

  static List<Value> resolveExpressionToList(Expression expression, Map<String, Value> variables) throws ResolveTemplateException {
    Value value = resolveExpression(expression, variables);
    return CastFunctions.castToList(value);
  }

  static Value resolveExpression(Expression expression, Map<String, Value> variables) throws ResolveTemplateException {
    Value[] values = makeVariables(expression.operands(), variables);
    try {
      return expression.compiledExpression().resolve(values);
    } catch (Exception e) {
      throw new ResolveTemplateException(e, "Failed to resolve expression {}", expression.statement());
    }
  }

  private static Value[] makeVariables(List<Operand> operands, Map<String, Value> variables) throws ResolveTemplateException {
    var values = new Value[operands.size()];
    int index = 0;
    for (Operand operand : operands) {
      if (operand.isLiteral()) {
        values[index++] = operand.asLiteral().value();
      } else {
        String variableName = operand.asVariable().name();
        Value variableValue = variables.get(variableName);
        if (variableValue == null) {
          throw new ResolveTemplateException("Variable by name {} is not found", variableName);
        }
        values[index++] = variableValue;
      }
    }
    return values;
  }

  private static String convertToString(Value value) throws ResolveTemplateException {
    return convertToString(value, false);
  }

  private static String convertToString(Value value, boolean isNested) throws ResolveTemplateException {
    if (value.type() == ValueTypes.Void) {
      return "void";
    } else if (value.type() == ValueTypes.Boolean || value.type() == ValueTypes.Integer || value.type() == ValueTypes.Real) {
      return CastFunctions.castToString(value.origin());
    } else if (value.type() == ValueTypes.String) {
      String string = ((StringValue) value.origin()).get();
      return isNested ? "\"" + string + "\"" : string;
    } else if (value.type() == ValueTypes.List) {
      List<Value> list = ((ListValue) value.origin()).get();
      return convertToString(list);
    } else if (value.type() == ValueTypes.Map) {
      Map<Value, Value> map = ((MapValue) value.origin()).get();
      return convertToString(map);
    } else {
      throw new UnexpectedViolationException("Unsupported value type: {}", value.typename().get());
    }
  }

  private static String convertToString(List<Value> list) throws ResolveTemplateException {
    var sb = new StringBuilder();
    sb.append("[");
    var first = true;
    for (Value value : list) {
      if (!first) {
        sb.append(",");
      }
      first = false;
      sb.append(convertToString(value, true));
    }
    sb.append("]");
    return sb.toString();
  }

  private static String convertToString(Map<Value, Value> map) throws ResolveTemplateException {
    var sb = new StringBuilder();
    sb.append("[");
    var first = true;
    for (Map.Entry<Value, Value> entry : map.entrySet()) {
      if (!first) {
        sb.append(",");
      }
      first = false;

      String key = convertToString(entry.getKey(), true);
      String value = convertToString(entry.getValue(), true);
      sb.append(key).append(":").append(value);
    }
    sb.append("]");
    return sb.toString();
  }
}
