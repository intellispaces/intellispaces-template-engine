package intellispaces.templateengine.function.parse;

import intellispaces.templateengine.function.common.StringFunctions;
import intellispaces.templateengine.model.element.StatementForeach;
import intellispaces.templateengine.model.element.StatementWhen;
import intellispaces.templateengine.model.element.StatementWhenBranch;
import intellispaces.templateengine.model.element.TemplateElementType;
import intellispaces.templateengine.model.element.TextElement;
import intellispaces.templateengine.object.TextBlockBuilder;
import intellispaces.templateengine.object.TextPositionBuilder;
import intellispaces.templateengine.object.TextTemplateBuilder;
import intellispaces.templateengine.object.element.MarkerElseBuilder;
import intellispaces.templateengine.object.element.MarkerEndBuilder;
import intellispaces.templateengine.object.element.MarkerForeachBuilder;
import intellispaces.templateengine.object.element.MarkerFormatBuilder;
import intellispaces.templateengine.object.element.MarkerFormatTypes;
import intellispaces.templateengine.object.element.MarkerPrintBuilder;
import intellispaces.templateengine.object.element.MarkerSetBuilder;
import intellispaces.templateengine.object.element.MarkerWhenBuilder;
import intellispaces.templateengine.object.element.StatementForeachBuilder;
import intellispaces.templateengine.object.element.StatementFormatBuilder;
import intellispaces.templateengine.object.element.StatementWhenBranchBuilder;
import intellispaces.templateengine.object.element.StatementWhenBuilder;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.object.element.TextElementBuilder;
import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.model.TextBlock;
import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.MutableTextPosition;
import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.element.MarkerFormatType;
import intellispaces.templateengine.model.element.MarkerWhen;
import intellispaces.templateengine.model.element.MarkerEnd;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.element.StatementFormat;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse template functions.
 */
public final class ParseTemplateFunctions {

  /**
   * Parse template.
   *
   * @param source template source.
   * @return parsed template.
   * @throws ParseTemplateException throws when template can't be parsed.
   */
  public static TextTemplate parseTemplate(String source) throws ParseTemplateException {
    return TextTemplateBuilder.build(
        analyzeStatements(
            glueElements(
                analyzeElements(
                    splitByMarkers(source)))));
  }

  /**
   * Splits template source into text blocks using markers.
   *
   * @param source template source.
   * @return list of text blocks.
   */
  static List<TextBlock> splitByMarkers(String source) {
    var blocks = new ArrayList<TextBlock>();
    char[] chars = source.toCharArray();
    MutableTextPosition curPosition = TextPositionBuilder.buildMutable(0, 1, 1);
    TextBlock block = readBlock(chars, curPosition);
    while (block != null && block.length() > 0) {
      movePosition(curPosition, block, chars);
      blocks.add(block);
      block = readBlock(chars, curPosition);
    }
    return blocks;
  }

  /**
   * Reads text block.
   *
   * @param source template source chars.
   * @param position read position.
   * @return text block or <code>null</code>.
   */
  static TextBlock readBlock(char[] source, TextPosition position) {
    if (position.offset() > source.length) {
      return null;
    }
    boolean marker = false;
    int curOffset = position.offset();
    if (isOpenDoubleCurlyBraces(source, curOffset)) {
      curOffset += 2;
      while (curOffset < source.length) {
        if (isCloseDoubleCurlyBraces(source, curOffset)) {
          marker = true;
          curOffset += 2;
          break;
        }
        curOffset++;
      }
    } else {
      while (curOffset < source.length) {
        if (isOpenDoubleCurlyBraces(source, curOffset)) {
          break;
        }
        curOffset++;
      }
    }
    return TextBlockBuilder.get()
        .position(TextPositionBuilder.build(position))
        .marker(marker)
        .value(readElementValue(marker, source, position.offset(), curOffset - position.offset()))
        .build();
  }

  private static boolean isOpenDoubleCurlyBraces(char[] source, int offset) {
    return offset + 1 < source.length && source[offset] == OPEN_CURLY_BRACE && source[offset + 1] == OPEN_CURLY_BRACE;
  }

  private static boolean isCloseDoubleCurlyBraces(char[] source, int offset) {
    return offset + 1 < source.length && source[offset] == CLOSE_CURLY_BRACE && source[offset + 1] == CLOSE_CURLY_BRACE;
  }

  private static String readElementValue(boolean marker, char[] source, int offset, int numChars) {
    if (marker) {
      // Exclude start and finish double curly braces
      return makeString(source, offset + 2, numChars - 4);
    } else {
      return makeString(source, offset, numChars);
    }
  }

  private static String makeString(char[] source, int position, int numChars) {
    return new String(source, position, numChars);
  }

  private static void movePosition(MutableTextPosition position, TextBlock block, char[] source) {
    int row = position.row();
    int column = position.column();
    for (int i = 0; i < block.length(); i++) {
      char curChar = source[position.offset() + i];
      if (curChar == '\r') {
        row++;
        column = 1;
      } else if (curChar != '\n') {
        column++;
      }
    }
    position.set(position.offset() + block.length(), row, column);
  }

  /**
   * Analyzes and maps text blocks to template elements.
   *
   * @param blocks list of text blocks.
   * @return list of template elements.
   * @throws ParseTemplateException throws when template can't be parsed.
   */
  private static List<TemplateElement> analyzeElements(List<TextBlock> blocks) throws ParseTemplateException {
    var elements = new ArrayList<TemplateElement>(blocks.size());
    for (var block : blocks) {
      if (block.isMarker()) {
        TemplateElement marker = analyzeMarker(block);
        elements.add(marker);
      } else {
        elements.add(TextElementBuilder.get()
            .position(block.position())
            .text(block.text())
            .build());
      }
    }
    return elements;
  }

  private static TemplateElement analyzeMarker(TextBlock block) throws ParseTemplateException {
    // Marker <print>
    var markerPrint = asMarkerPrint(block);
    if (markerPrint != null) {
      return markerPrint;
    }

    // Short marker <print>
    var shortMarkerPrint = asShortMarkerPrint(block);
    if (shortMarkerPrint != null) {
      return shortMarkerPrint;
    }

    // Marker <set>
    var markerSet = asMarkerSet(block);
    if (markerSet != null) {
      return markerSet;
    }

    // Marker <format>
    var markerFormat = asMarkerFormat(block);
    if (markerFormat != null) {
      return markerFormat;
    }

    // Marker <foreach>
    var markerForeach = asMarkerForeach(block);
    if (markerForeach != null) {
      return markerForeach;
    }

    // Marker <when>
    var markerWhen = asMarkerWhen(block);
    if (markerWhen != null) {
      return markerWhen;
    }

    // Marker <else>
    var markerElse = asMarkerElse(block);
    if (markerElse != null) {
      return markerElse;
    }

    // Marker <end>
    var markerEnd = asMarkerEnd(block);
    if (markerEnd != null) {
      return markerEnd;
    }

    throw new ParseTemplateException("Invalid template marker at position {}:{}", block.position().row(), block.position().column());
  }

  private static MarkerPrint asMarkerPrint(TextBlock block) throws ParseTemplateException {
    Matcher matcher = MARKER_PRINT_PATTERN.matcher(block.value());
    if (matcher.matches()) {
      return MarkerPrintBuilder.get()
          .position(block.position())
          .outputExpression(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .build();
    }
    return null;
  }

  private static MarkerPrint asShortMarkerPrint(TextBlock block) throws ParseTemplateException {
    Matcher matcher = MARKER_PRINT_SHORT_PATTERN.matcher(block.value());
    if (matcher.matches()) {
      return MarkerPrintBuilder.get()
          .position(block.position())
          .outputExpression(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .build();
    }
    return null;
  }

  private static MarkerSet asMarkerSet(TextBlock block) throws ParseTemplateException {
    Matcher matcher = MARKER_SET_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      return MarkerSetBuilder.get()
          .position(block.position())
          .valueName(matcher.group(1).trim())
          .valueExpression(ParseExpressionFunctions.parseExpression(matcher.group(2).trim()))
          .build();
    }
    return null;
  }

  private static MarkerFormat asMarkerFormat(TextBlock block)  {
    Matcher matcher = MARKER_FORMAT_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      return MarkerFormatBuilder.get()
          .position(block.position())
          .types(Arrays.stream(matcher.group(1).split(","))
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(MarkerFormatTypes::valueOf)
              .map(t -> (MarkerFormatType) t)
              .toList())
          .build();
    }
    return null;
  }

  private static MarkerForeach asMarkerForeach(TextBlock block) throws ParseTemplateException {
    Matcher matcher = MARKER_FOREACH_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      return MarkerForeachBuilder.get()
          .position(block.position())
          .itemName(matcher.group(1))
          .collectionExpression(ParseExpressionFunctions.parseExpression(matcher.group(2).trim()))
          .build();
    }
    return null;
  }

  private static MarkerWhen asMarkerWhen(TextBlock block) throws ParseTemplateException {
    var matcher = MARKER_WHEN_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      return MarkerWhenBuilder.get()
          .position(block.position())
          .condition(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .build();
    }
    return null;
  }

  private static MarkerElse asMarkerElse(TextBlock block) throws ParseTemplateException {
    Matcher matcher = MARKER_ELSE_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      String condition = matcher.group(2);
      return MarkerElseBuilder.get()
          .position(block.position())
          .condition(condition != null ? ParseExpressionFunctions.parseExpression(condition.trim()) : null)
          .build();
    }
    return null;
  }

  private static MarkerEnd asMarkerEnd(TextBlock block) {
    Matcher matcher = MARKER_END_PATTERN.matcher(block.text());
    if (matcher.matches()) {
      return MarkerEndBuilder.get()
          .position(block.position())
          .build();
    }
    return null;
  }

  private static List<TemplateElement> glueElements(List<TemplateElement> elements) {
    List<TemplateElement> result = new ArrayList<>(elements);
    for (int ind = 0; ind < elements.size(); ind++) {
      if (isOneLineHiddenElement(elements, ind)) {
        if (ind > 0) {
          TemplateElement prevElement = result.get(ind - 1);
          result.set(ind - 1,
              TextElementBuilder.get()
                  .position(prevElement.position())
                  .text(StringFunctions.removeLastBlanks(((TextElement) prevElement).text()))
                  .build()
          );
        }
        if (ind < elements.size() - 1) {
          TemplateElement nextElement = result.get(ind + 1);
          result.set(ind + 1,
              TextElementBuilder.get()
                  .position(nextElement.position())
                  .text(StringFunctions.removeFirstBlanksAndLineBreak(((TextElement) nextElement).text()))
                  .build()
          );
        }
      }
    }
    return result;
  }

  private static boolean isOneLineHiddenElement(List<TemplateElement> elements, int index) {
    TemplateElement prevElement = index > 0 ? elements.get(index - 1) : null;
    TemplateElement curElement = elements.get(index);
    TemplateElement nextElement = index < elements.size() - 1 ? elements.get(index + 1) : null;
    return isOneLineHiddenElement(prevElement, curElement, nextElement);
  }

  private static boolean isOneLineHiddenElement(TemplateElement prevElement, TemplateElement curElement, TemplateElement nextElement) {
    return (prevElement == null || isEndWithLineBreakIgnoreBlanks(prevElement))
        && isHiddenElement(curElement.type())
        && (nextElement == null || isBeginWithLineBreakIgnoreBlanks(nextElement));
  }

  private static boolean isEndWithLineBreakIgnoreBlanks(TemplateElement element) {
    return element.type() == TemplateElementTypes.Text && StringFunctions.isEndWithLineBreakIgnoreBlanks(((TextElement) element).text());
  }

  private static boolean isBeginWithLineBreakIgnoreBlanks(TemplateElement element) {
    return element.type() == TemplateElementTypes.Text && StringFunctions.isBeginWithLineBreakIgnoreBlanks(((TextElement) element).text());
  }

  private static boolean isHiddenElement(TemplateElementType elementType) {
    return TemplateElementTypes.MarkerSet == elementType
        || TemplateElementTypes.MarkerForeach == elementType
        || TemplateElementTypes.MarkerFormat == elementType
        || TemplateElementTypes.MarkerWhen == elementType
        || TemplateElementTypes.MarkerElse == elementType
        || TemplateElementTypes.MarkerEnd == elementType;
  }

  private static List<TemplateElement> analyzeStatements(List<TemplateElement> elements) throws ParseTemplateException {
    return analyzeStatements(elements.listIterator());
  }

  private static List<TemplateElement> analyzeStatements(ListIterator<TemplateElement> iterator) throws ParseTemplateException {
    var resultElements = new ArrayList<TemplateElement>();
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerFormat.equals(element.type())) {
        MarkerFormat markerFormat = (MarkerFormat) element;
        StatementFormat statementFormat = readStatementFormat(markerFormat, iterator);
        resultElements.add(statementFormat);
      } else if (TemplateElementTypes.MarkerForeach.equals(element.type())) {
        MarkerForeach markerForeach = (MarkerForeach) element;
        StatementForeach statementForeach = readStatementForeach(markerForeach, iterator);
        resultElements.add(statementForeach);
    } else if (TemplateElementTypes.MarkerWhen.equals(element.type())) {
        MarkerWhen markerWhen = (MarkerWhen) element;
        StatementWhen statementWhen = readStatementWhen(markerWhen, iterator);
        resultElements.add(statementWhen);
      } else {
        resultElements.add(element);
      }
    }
    return resultElements;
  }

  private static StatementFormat readStatementFormat(
      MarkerFormat markerFormat, ListIterator<TemplateElement> iterator
  ) throws ParseTemplateException {
    List<TemplateElement> subElements = readUpToMarkerEnd(markerFormat, iterator);
    List<TemplateElement> subStatements = analyzeStatements(subElements);
    return StatementFormatBuilder.get()
        .position(markerFormat.position())
        .types(markerFormat.types())
        .subElements(subStatements)
        .build();
  }

  private static StatementForeach readStatementForeach(
      MarkerForeach markerForeach, ListIterator<TemplateElement> iterator
  ) throws ParseTemplateException {
    List<TemplateElement> subElements = readUpToMarkerEnd(markerForeach, iterator);
    List<TemplateElement> subStatements = analyzeStatements(subElements);
    return StatementForeachBuilder.get()
        .position(markerForeach.position())
        .collectionExpression(markerForeach.collectionExpression())
        .itemName(markerForeach.itemName())
        .subElements(subStatements)
        .build();
  }

  private static StatementWhen readStatementWhen(
      MarkerWhen markerWhen, ListIterator<TemplateElement> iterator
  ) throws ParseTemplateException {
    List<StatementWhenBranch> branches = new ArrayList<>();
    StatementWhenBranch defaultBranch = null;

    List<TemplateElement> curBranchElements = readWhenBranch(markerWhen, iterator);
    curBranchElements = analyzeStatements(curBranchElements);
    branches.add(StatementWhenBranchBuilder.get()
        .condition(markerWhen.condition())
        .subElements(curBranchElements)
        .build());
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerElse == element.type()) {
        MarkerElse markerElse = (MarkerElse) element;
        curBranchElements = readWhenBranch(element, iterator);
        curBranchElements = analyzeStatements(curBranchElements);
        StatementWhenBranch branch = StatementWhenBranchBuilder.get()
            .condition(markerElse.condition())
            .subElements(curBranchElements)
            .build();
        if (markerElse.condition() == null) {
          defaultBranch = branch;
        } else {
          branches.add(branch);
        }
      } else if (TemplateElementTypes.MarkerEnd == element.type()) {
        break;
      } else {
        throw new ParseTemplateException("Unexpected element of type {} at position {}:{}. Expected markers {{else}} or {{end}}",
            element.position().row(), element.position().column());
      }
    }
    return StatementWhenBuilder.get()
        .position(markerWhen.position())
        .branches(branches)
        .defaultBranch(defaultBranch)
        .build();
  }

  private static List<TemplateElement> readWhenBranch(
      TemplateElement conditionElement, ListIterator<TemplateElement> iterator
  ) throws ParseTemplateException {
    List<TemplateElement> branchElements = new ArrayList<>();
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerElse == element.type() || TemplateElementTypes.MarkerEnd == element.type()) {
        iterator.previous();
        return branchElements;
      } else if (TemplateElementTypes.MarkerWhen == element.type()) {
        TemplateElement nestedWhen = readStatementWhen((MarkerWhen) element, iterator);
        branchElements.add(nestedWhen);
      } else {
        branchElements.add(element);
      }
    }
    throw new ParseTemplateException("End marker missing for statement marker at position {}:{}",
        conditionElement.position().row(), conditionElement.position().column());
  }

  private static List<TemplateElement> readUpToMarkerEnd(
      TemplateElement startElement, ListIterator<TemplateElement> iterator
  ) throws ParseTemplateException {
    List<TemplateElement> subElements = new ArrayList<>();
    int counter = 1;
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (isStatementBegin(element)) {
        counter++;
      } else if (TemplateElementTypes.MarkerEnd == element.type()) {
        counter--;
        if (counter == 0) {
          return subElements;
        }
      }
      subElements.add(element);
    }
    throw new ParseTemplateException("End marker missing for statement marker at position {}:{}", startElement.position().row(), startElement.position().column());
  }

  private static boolean isStatementBegin(TemplateElement element) {
    return TemplateElementTypes.MarkerFormat == element.type()
        || TemplateElementTypes.MarkerWhen == element.type()
        || TemplateElementTypes.MarkerForeach == element.type();
  }

  private ParseTemplateFunctions() {}

  private static final char OPEN_CURLY_BRACE = '{';
  private static final char CLOSE_CURLY_BRACE = '}';
  private static final String IDENTIFIER_CHARS = "\\w";
  private static final String EXPRESSION_CHARS = " \\t\\w\\.,:\\(\\)\\[\\]\"'";
  private static final Pattern MARKER_SET_PATTERN = Pattern.compile("\\{\\{set[ \\t]+(\\w+)[ \\t]*=[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)\\}\\}");
  private static final Pattern MARKER_PRINT_PATTERN = Pattern.compile("print[ \\t]+([\\$" + EXPRESSION_CHARS + "]+)");
  private static final Pattern MARKER_PRINT_SHORT_PATTERN = Pattern.compile("[ \\t]*(\\$[" + IDENTIFIER_CHARS + "]+[" + EXPRESSION_CHARS + "]*)[ \\t]*");
  private static final Pattern MARKER_FORMAT_PATTERN = Pattern.compile("\\{\\{format[ \\t]+([\\w, \\t]+)\\}\\}");
  private static final Pattern MARKER_FOREACH_PATTERN = Pattern.compile("\\{\\{for[ \\t]+([" + IDENTIFIER_CHARS + "]+)[ \\t]*:[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)[ \\t]*\\}\\}");
  private static final Pattern MARKER_WHEN_PATTERN = Pattern.compile("\\{\\{when[ \\t]+([\\$" + EXPRESSION_CHARS + "]+)\\}\\}");
  private static final Pattern MARKER_ELSE_PATTERN = Pattern.compile("\\{\\{else[ \\t]*(when[ \\t]+([\\$" + EXPRESSION_CHARS + "]+))?\\}\\}");
  private static final Pattern MARKER_END_PATTERN = Pattern.compile("\\{\\{end[ \\t\\w]*\\}\\}");
}
