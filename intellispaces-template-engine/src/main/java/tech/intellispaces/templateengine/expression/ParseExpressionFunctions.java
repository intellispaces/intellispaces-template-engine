package tech.intellispaces.templateengine.expression;

import tech.intellispaces.general.exception.UnexpectedExceptions;
import tech.intellispaces.general.text.CharFunctions;
import tech.intellispaces.templateengine.exception.ParseTemplateException;
import tech.intellispaces.templateengine.exception.ParseTemplateExceptions;
import tech.intellispaces.templateengine.expression.compilation.CompileFunctions;
import tech.intellispaces.templateengine.expression.value.BooleanValues;
import tech.intellispaces.templateengine.expression.value.IntegerValues;
import tech.intellispaces.templateengine.expression.value.ListValues;
import tech.intellispaces.templateengine.expression.value.MapValues;
import tech.intellispaces.templateengine.expression.value.RealValues;
import tech.intellispaces.templateengine.expression.value.StringValues;
import tech.intellispaces.templateengine.expression.value.Value;
import tech.intellispaces.templateengine.expression.value.VoidValues;
import tech.intellispaces.templateengine.source.SourceFunctions;

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

  private ParseExpressionFunctions() {}

  /**
   * Parses expression.
   *
   * @param statement expression statement.
   * @return parsed expression.
   * @throws ParseTemplateException throws when expression can't be parsed.
   */
  public static Expression parseExpression(String statement) throws ParseTemplateException {
    ValidateExpressionFunctions.validateExpression(statement);

    List<Operand> operands = new ArrayList<>();
    String preparedStatement = prepareStatement(statement, operands);
    CompiledExpression compiledExpression = compileExpression(preparedStatement);
    return Expressions.build()
        .statement(statement)
        .preparedStatement(preparedStatement)
        .compiledExpression(compiledExpression)
        .operands(operands)
        .get();
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
      if (curChar == '.' && nextChar == '"') {
        // Replace to "get" operation
        String name = readString(chars, ind + 1);
        preparedStatement.append(".get(");
        appendStringLiteral(preparedStatement, name, operands, operandWord2IndexMap);
        preparedStatement.append(")");
        ind += name.length() + 3;
      } else if (curChar == '"') {
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
      } else if (SourceFunctions.isWordChar(curChar)) {
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
          appendLiteral(
              preparedStatement, valueAndWording.wording(), operands, operandWord2IndexMap, valueAndWording.value()
          );
          ind += valueAndWording.wording().length();
        } else {
          // Replace to "get" operation
          String subStatement = readFetchOperand(chars, ind);
          String preparedSubExpression = prepareStatement(subStatement, operands, operandWord2IndexMap);
          preparedStatement.append(".get(");
          preparedStatement.append(preparedSubExpression);
          preparedStatement.append(")");
          ind += subStatement.length() + 2;
        }
      } else {
        preparedStatement.append(curChar);
        ind++;
      }
    }
    return "(" + Value.class.getCanonicalName() + ") (" + preparedStatement + ")";
  }

  private static ValueAndWording readListOrMap(char[] chars, int beginIndex) {
    List<Value> values = new ArrayList<>();
    boolean isList = false;
    int ind = beginIndex + 1;
    while (ind < chars.length) {
      char curChar = chars[ind];
      char nextChar = ind + 1 < chars.length ? chars[ind + 1] : 0;
      if (CharFunctions.isGapChar(curChar)) {
        ind++;
      } else if (curChar == '"') {
        String string = readString(chars, ind);
        ind += string.length() + 2;
        values.add(StringValues.of(string));
      } else if (isDigit(curChar) || ((curChar == '+' || curChar == '-') && isDigit(nextChar))) {
        String number = readNumber(chars, ind);
        ind += number.length();
        values.add(parseNumber(number));
      } else if (SourceFunctions.isWordChar(curChar)) {
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
        throw UnexpectedExceptions.withMessage("Unknown character {0}", curChar);
      }
    }

    final Value value;
    if (isList) {
      value = ListValues.of(values);
    } else {
      Map<Value, Value> map = new HashMap<>();
      for (int i = 0; i < values.size(); i += 2) {
        map.put(values.get(i), values.get(i + 1));
      }
      value = MapValues.of(map);
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
    while (ind < chars.length && SourceFunctions.isWordChar(chars[ind])) {
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
    throw ParseTemplateExceptions.withMessage("Invalid expression at column {0}. Expected closed square bracket",
        beginIndex);
  }

  private static Optional<Literal> parseKeyword(String word) {
    if (Keywords.True.word().equals(word)) {
      return Optional.of(Literals.build().value(BooleanValues.of(true)).get());
    } else if (Keywords.False.word().equals(word)) {
      return Optional.of(Literals.build().value(BooleanValues.of(false)).get());
    } else if (Keywords.Void.word().equals(word)) {
      return Optional.of(Literals.build().value(VoidValues.get()).get());
    }
    return Optional.empty();
  }

  private static void appendStringLiteral(
      StringBuilder preparedStatement,
      String string,
      List<Operand> operands,
      Map<String, Integer> operandWord2IndexMap
  ) {
    appendLiteral(
        preparedStatement, "\"" + string + "\"", operands, operandWord2IndexMap, StringValues.of(string)
    );
  }

  private static void appendNumberLiteral(
      StringBuilder preparedStatement,
      String number,
      List<Operand> operands,
      Map<String, Integer> operandWord2IndexMap
  ) {
    Value value = parseNumber(number);
    appendLiteral(preparedStatement, number, operands, operandWord2IndexMap, value);
  }

  private static void appendLiteral(
      StringBuilder preparedStatement,
      String word,
      List<Operand> operands,
      Map<String, Integer> operandWord2IndexMap,
      Value value
  ) {
    Literal literal = Literals.build().value(value).get();
    appendOperand(preparedStatement, word, literal, operands, operandWord2IndexMap);
  }

  private static void appendVariable(
      StringBuilder preparedStatement,
      String variableName,
      List<Operand> operands,
      Map<String, Integer> operandWord2IndexMap
  ) {
    Variable variable = Variables.build().name(variableName).get();
    appendOperand(preparedStatement, "$" + variableName, variable, operands, operandWord2IndexMap);
  }

  private static void appendOperand(
      StringBuilder preparedStatement,
      String word,
      Operand operand,
      List<Operand> operands,
      Map<String, Integer> operandWord2IndexMap
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
    return isRealNumber(number)
        ? RealValues.of(Double.parseDouble(number))
        : IntegerValues.of(Integer.parseInt(number));
  }

  private static boolean isRealNumber(String value) {
    return value.contains(".");
  }

  private static CompiledExpression compileExpression(
      String preparedStatement
  ) throws ParseTemplateException {
    synchronized (STATEMENTS_CACHE) {
      StatementKey key = STATEMENTS_CACHE.keySet().stream()
          .filter(k -> k.statement().equals(preparedStatement))
          .findAny()
          .orElse(null);
      CompiledExpression compiledExpression = (key != null ? STATEMENTS_CACHE.get(key) : null);
      if (compiledExpression == null) {
        compiledExpression = CompileFunctions.compileExpression(preparedStatement);
        STATEMENTS_CACHE.put(new StatementKey(preparedStatement), compiledExpression);
      }
      return compiledExpression;
    }
  }

  private record ValueAndWording(Value value, String wording) {}

  private record StatementKey(String statement) {}

  private static final Map<StatementKey, CompiledExpression> STATEMENTS_CACHE = new WeakHashMap<>();
}
