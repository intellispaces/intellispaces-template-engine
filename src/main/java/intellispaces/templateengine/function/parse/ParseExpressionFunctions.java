package intellispaces.templateengine.function.parse;

import intellispaces.commons.CharFunctions;
import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.templateengine.object.expression.ExpressionBuilder;
import intellispaces.templateengine.object.expression.Keywords;
import intellispaces.templateengine.object.expression.LiteralBuilder;
import intellispaces.templateengine.object.expression.VariableBuilder;
import intellispaces.templateengine.object.value.BooleanValueBuilder;
import intellispaces.templateengine.object.value.ListValueBuilder;
import intellispaces.templateengine.object.value.MapValueBuilder;
import intellispaces.templateengine.object.value.RealValueBuilder;
import intellispaces.templateengine.object.value.IntegerValueBuilder;
import intellispaces.templateengine.object.value.StringValueBuilder;
import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.function.common.StringFunctions;
import intellispaces.templateengine.function.compile.CompileFunctions;
import intellispaces.templateengine.function.compile.CompiledStatement;
import intellispaces.templateengine.function.validate.ValidateExpressionFunctions;
import intellispaces.templateengine.object.value.VoidValues;
import intellispaces.templateengine.model.expression.Expression;
import intellispaces.templateengine.model.expression.Literal;
import intellispaces.templateengine.model.expression.Operand;
import intellispaces.templateengine.model.expression.Variable;
import intellispaces.templateengine.model.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import static java.lang.Character.isDigit;

/**
 * Parse expression functions.
 */
public final class ParseExpressionFunctions {

  /**
   * Parses expression.
   *
   * @param statement expression statement.
   * @return parsed expression.
   * @throws ParseTemplateException throws when expression can't be parsed.
   */
  public static Expression parseExpression(String statement) throws ParseTemplateException {
    ValidateExpressionFunctions.validateExpression(statement);

    var operands = new ArrayList<Operand>();
    String preparedStatement = prepareStatement(statement, operands);
    CompiledStatement compiledStatement = compileStatement(preparedStatement);
    return ExpressionBuilder.get()
        .statement(statement)
        .preparedStatement(preparedStatement)
        .compiledExpression(compiledStatement)
        .operands(operands)
        .build();
  }

  private static String prepareStatement(String statement, List<Operand> operands) throws ParseTemplateException {
    return prepareStatement(statement, operands, new HashMap<>());
  }

  private static String prepareStatement(
      String statement, List<Operand> operands, Map<String, Integer> operandWord2IndexMap
  ) throws ParseTemplateException {
    char[] chars = statement.toCharArray();
    StringBuilder preparedStatement = new StringBuilder(chars.length);
    int ind = 0;
    while (ind < chars.length) {
      char curChar = chars[ind];
      char nextChar = ind + 1 < chars.length ? chars[ind + 1] : 0;
      if (curChar == '"') {
        String string = readString(chars, ind);
        ind += string.length() + 2;
        appendStringLiteral(preparedStatement, string, operands, operandWord2IndexMap);
      } else if (isDigit(curChar) || ((curChar == '+' || curChar == '-') && isDigit(nextChar))) {
        String number = readNumber(chars, ind);
        ind += number.length();
        appendNumberLiteral(preparedStatement, number, operands, operandWord2IndexMap);
      } else if (curChar == '$') {
        String variableName = readWord(chars, ind + 1);
        ind += variableName.length() + 1;
        appendVariable(preparedStatement, variableName, operands, operandWord2IndexMap);
      } else if (StringFunctions.isWordChar(curChar)) {
        String word = readWord(chars, ind);
        Optional<Literal> keyword = parseKeyword(word);
        if (keyword.isPresent()) {
          appendOperand(preparedStatement, word, keyword.get(), operands, operandWord2IndexMap);
        } else {
          preparedStatement.append(word);
        }
        ind += word.length();
      } else if (curChar == '[') {
        if (ind == 0) {
          ValueAndWording valueAndWording = readListOrMap(chars, ind);
          appendLiteral(preparedStatement, valueAndWording.wording(), operands, operandWord2IndexMap, valueAndWording.value());
          ind += valueAndWording.wording().length();
        } else {
          // Replace to <fetch> operation
          String subStatement = readFetchOperand(chars, ind);
          String preparedSubExpression = prepareStatement(subStatement, operands, operandWord2IndexMap);
          preparedStatement.append(".fetch(");
          preparedStatement.append(preparedSubExpression);
          preparedStatement.append(")");
          ind += subStatement.length() + 2;
        }
      } else {
        preparedStatement.append(curChar);
        ind++;
      }
    }
    return preparedStatement.toString();
  }

  private static ValueAndWording readListOrMap(char[] chars, int beginIndex) {
    var values = new ArrayList<Value>();
    boolean isList = false;
    int ind = beginIndex + 1;
    while (ind < chars.length) {
      char curChar = chars[ind];
      char nextChar = ind + 1 < chars.length ? chars[ind + 1] : 0;
      if (CharFunctions.isBlankChar(curChar)) {
        ind++;
      } else if (curChar == '"') {
        String string = readString(chars, ind);
        ind += string.length() + 2;
        values.add(StringValueBuilder.build(string));
      } else if (isDigit(curChar) || ((curChar == '+' || curChar == '-') && isDigit(nextChar))) {
        String number = readNumber(chars, ind);
        ind += number.length();
        values.add(parseNumber(number));
      } else if (StringFunctions.isWordChar(curChar)) {
        String word = readWord(chars, ind);
        Optional<Literal> keyword = parseKeyword(word);
        keyword.ifPresent(literal -> values.add(literal.value()));
        ind += word.length();
      } else if (curChar == ',') {
        if (values.size() == 1) {
          isList = true;
        }
        ind++;
      } else if (curChar == ':') {
        ind++;
      } else if (curChar == ']') {
        break;
      } else {
        throw new UnexpectedViolationException("Unknown character {}", curChar);
      }
    }

    final Value value;
    if (isList) {
      value = ListValueBuilder.build(values);
    } else {
      var map = new HashMap<Value, Value>();
      for (int i = 0; i < values.size(); i += 2) {
        map.put(values.get(i), values.get(i + 1));
      }
      value = MapValueBuilder.build(map);
    }
    return new ValueAndWording(value, new String(chars, beginIndex, ind - beginIndex + 1));
  }

  private static String readString(char[] chars, int beginIndex) {
    int ind = beginIndex + 1;
    while (ind < chars.length && chars[ind] != '"') {
      ind++;
    }
    return new String(chars, beginIndex + 1, ind - beginIndex - 1);
  }

  private static String readNumber(char[] chars, int beginIndex) {
    // Skip sign or first digit
    int ind = beginIndex + 1;

    int dotIndex = -1;
    while (ind < chars.length) {
      char curChar = chars[ind];
      if (curChar == '.') {
        if (dotIndex >= 0) {
          break;
        }
        dotIndex = ind;
      } else if (!isDigit(curChar)) {
        if (dotIndex + 1 == ind) {
          // When not digit character follows after dot back to last digit
          ind--;
        }
        break;
      }
      ind++;
    }
    return new String(chars, beginIndex, ind - beginIndex);
  }

  private static String readWord(char[] chars, int beginIndex) {
    int ind = beginIndex;
    while (ind < chars.length && StringFunctions.isWordChar(chars[ind])) {
      ind++;
    }
    return new String(chars, beginIndex, ind - beginIndex);
  }

  private static String readFetchOperand(char[] chars, int beginIndex) throws ParseTemplateException {
    int bracketCounter = 0;
    for (int ind = beginIndex; ind < chars.length; ind++) {
      if (chars[ind] == '[') {
        bracketCounter++;
      } else if (chars[ind] == ']') {
        bracketCounter--;
        if (bracketCounter == 0) {
          return new String(chars, beginIndex + 1, ind - beginIndex - 1);
        }
      }
    }
    throw new ParseTemplateException("Invalid expression at column {}. Expected closed square bracket", beginIndex);
  }

  private static Optional<Literal> parseKeyword(String word) {
    if (Keywords.True.word().equals(word)) {
      return Optional.of(LiteralBuilder.get().value(BooleanValueBuilder.build(true)).build());
    } else if (Keywords.False.word().equals(word)) {
      return Optional.of(LiteralBuilder.get().value(BooleanValueBuilder.build(false)).build());
    } else if (Keywords.Void.word().equals(word)) {
      return Optional.of(LiteralBuilder.get().value(VoidValues.get()).build());
    }
    return Optional.empty();
  }

  private static void appendStringLiteral(
      StringBuilder preparedStatement, String string, List<Operand> operands, Map<String, Integer> operandWord2IndexMap
  ) {
    appendLiteral(preparedStatement, "\"" + string + "\"", operands, operandWord2IndexMap, StringValueBuilder.build(string));
  }

  private static void appendNumberLiteral(
      StringBuilder preparedStatement, String number, List<Operand> operands, Map<String, Integer> operandWord2IndexMap
  ) {
    Value value = parseNumber(number);
    appendLiteral(preparedStatement, number, operands, operandWord2IndexMap, value);
  }

  private static void appendLiteral(
      StringBuilder preparedStatement, String word, List<Operand> operands, Map<String, Integer> operandWord2IndexMap, Value value
  ) {
    Literal literal = LiteralBuilder.get().value(value).build();
    appendOperand(preparedStatement, word, literal, operands, operandWord2IndexMap);
  }

  private static void appendVariable(
      StringBuilder preparedStatement, String variableName, List<Operand> operands, Map<String, Integer> operandWord2IndexMap
  ) {
    Variable variable = VariableBuilder.get().name(variableName).build();
    appendOperand(preparedStatement, "$" + variableName, variable, operands, operandWord2IndexMap);
  }

  private static void appendOperand(
      StringBuilder preparedStatement, String word, Operand operand, List<Operand> operands, Map<String, Integer> operandWord2IndexMap
  ) {
    int operandIndex = operandWord2IndexMap.computeIfAbsent(word, k -> {
      operands.add(operand);
      return operandWord2IndexMap.size();
    });
    appendOperand(preparedStatement, operandIndex);
  }

  private static void appendOperand(StringBuilder preparedStatement, int operandIndex) {
    preparedStatement.append("operands[").append(operandIndex).append("]");
  }

  private static Value parseNumber(String number) {
    return isRealNumber(number) ? RealValueBuilder.build(Double.parseDouble(number)) : IntegerValueBuilder.build(Integer.parseInt(number));
  }

  private static boolean isRealNumber(String value) {
    return value.contains(".");
  }

  private static CompiledStatement compileStatement(String preparedStatement) throws ParseTemplateException {
    synchronized (STATEMENTS_CACHE) {
      StatementKey key = STATEMENTS_CACHE.keySet().stream()
          .filter(k -> k.statement().equals(preparedStatement))
          .findAny()
          .orElse(null);
      CompiledStatement compiledExpression = (key != null ? STATEMENTS_CACHE.get(key) : null);
      if (compiledExpression == null) {
        compiledExpression = CompileFunctions.compileExpression(preparedStatement);
        STATEMENTS_CACHE.put(new StatementKey(preparedStatement), compiledExpression);
      }
      return compiledExpression;
    }
  }

  private ParseExpressionFunctions() {}

  private static record ValueAndWording(Value value, String wording) {}

  private static record StatementKey(String statement) {}

  private static final Map<StatementKey, CompiledStatement> STATEMENTS_CACHE = new WeakHashMap<>();
}
