package tech.intellispaces.templates.template;

import tech.intellispaces.templates.exception.ParseTemplateException;
import tech.intellispaces.templates.exception.ResolveTemplateException;
import tech.intellispaces.templates.template.element.ElementContexts;
import tech.intellispaces.templates.template.element.ElseMarkers;
import tech.intellispaces.templates.template.element.EndMarkers;
import tech.intellispaces.templates.template.element.ForeachMarkers;
import tech.intellispaces.templates.template.element.ForeachStatements;
import tech.intellispaces.templates.template.element.FormatMarkers;
import tech.intellispaces.templates.template.element.FormatStatements;
import tech.intellispaces.templates.template.element.MarkerElse;
import tech.intellispaces.templates.template.element.MarkerEnd;
import tech.intellispaces.templates.template.element.MarkerForeach;
import tech.intellispaces.templates.template.element.MarkerFormat;
import tech.intellispaces.templates.template.element.MarkerFormatType;
import tech.intellispaces.templates.template.element.MarkerFormatTypes;
import tech.intellispaces.templates.template.element.MarkerPrint;
import tech.intellispaces.templates.template.element.MarkerSet;
import tech.intellispaces.templates.template.element.MarkerWhen;
import tech.intellispaces.templates.template.element.PrintMarkers;
import tech.intellispaces.templates.template.element.SetMarkers;
import tech.intellispaces.templates.template.element.StatementForeach;
import tech.intellispaces.templates.template.element.StatementFormat;
import tech.intellispaces.templates.template.element.StatementWhen;
import tech.intellispaces.templates.template.element.StatementWhenBranch;
import tech.intellispaces.templates.template.element.TemplateElement;
import tech.intellispaces.templates.template.element.TemplateElementType;
import tech.intellispaces.templates.template.element.TemplateElementTypes;
import tech.intellispaces.templates.template.element.TextElement;
import tech.intellispaces.templates.template.element.TextElements;
import tech.intellispaces.templates.template.element.WhenBranchStatements;
import tech.intellispaces.templates.template.element.WhenMarkers;
import tech.intellispaces.templates.template.element.WhenStatements;
import tech.intellispaces.templates.template.expression.ParseExpressionFunctions;
import tech.intellispaces.templates.template.expression.value.Value;
import tech.intellispaces.templates.template.expression.value.ValueFunctions;
import tech.intellispaces.templates.template.source.SourceFunctions;
import tech.intellispaces.templates.template.source.block.Block;
import tech.intellispaces.templates.template.source.block.Blocks;
import tech.intellispaces.templates.template.source.position.MutablePosition;
import tech.intellispaces.templates.template.source.position.Position;
import tech.intellispaces.templates.template.source.position.Positions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Template functions.
 */
public final class TemplateFunctions {

  /**
   * Parse template.
   *
   * @param source template source.
   * @return parsed template.
   * @throws ParseTemplateException throws when template can't be parsed.
   */
  public static Template parseTemplate(String source) throws ParseTemplateException {
    return Templates.of(
        analyzeStatements(
            glueElements(
                analyzeElements(
                    split(source)))));
  }

  /**
   * Resolve template.
   *
   * @param template the template.
   * @param variables variables.
   * @return resolving template.
   * @throws ResolveTemplateException throws when template can't be resolved.
   */
  public static String resolveTemplate(
      Template template, Map<String, Object> variables
  ) throws ResolveTemplateException {
    Map<String, Value> values = new HashMap<>();
    for (Map.Entry<String, Object> entry : variables.entrySet()) {
      values.put(entry.getKey(), ValueFunctions.objectToValue(entry.getValue()));
    }

    var sb = new StringBuilder();
    for (TemplateElement element : template.elements()) {
      sb.append(element.resolve(values));
    }
    return sb.toString();
  }

  /**
   * Splits template source into text blocks using markers.
   *
   * @param source template source.
   * @return list of text blocks.
   */
  public static List<Block> split(String source) {
    List<Block> blocks = new ArrayList<>();
    char[] chars = source.toCharArray();
    MutablePosition curPosition = Positions.mutable(0, 1, 1);
    Block block = readBlock(chars, curPosition);
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
  public static Block readBlock(char[] source, Position position) {
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
    return Blocks.build()
        .position(Positions.of(position))
        .marker(marker)
        .value(readElementValue(marker, source, position.offset(), curOffset - position.offset()))
        .get();
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

  private static void movePosition(MutablePosition position, Block block, char[] source) {
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
  private static List<TemplateElement> analyzeElements(
      List<Block> blocks
  ) throws ParseTemplateException {
    List<TemplateElement> elements = new ArrayList<>(blocks.size());
    int elementIndex = 0;
    for (Block block : blocks) {
      if (block.isMarker()) {
        TemplateElement marker = analyzeMarker(block, elements, elementIndex);
        elements.add(marker);
      } else {
        elements.add(TextElements.build()
            .context(ElementContexts.build()
                .position(block.position())
                .templateElements(elements)
                .elementIndex(elementIndex)
                .get())
            .text(block.wording())
            .get());
      }
      elementIndex++;
    }
    return elements;
  }

  private static TemplateElement analyzeMarker(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    // Marker <print>
    MarkerPrint markerPrint = asMarkerPrint(block, elements, elementIndex);
    if (markerPrint != null) {
      return markerPrint;
    }

    // Short marker <print>
    MarkerPrint shortMarkerPrint = asShortMarkerPrint(block, elements, elementIndex);
    if (shortMarkerPrint != null) {
      return shortMarkerPrint;
    }

    // Marker <set>
    MarkerSet markerSet = asMarkerSet(block, elements, elementIndex);
    if (markerSet != null) {
      return markerSet;
    }

    // Marker <format>
    MarkerFormat markerFormat = asMarkerFormat(block, elements, elementIndex);
    if (markerFormat != null) {
      return markerFormat;
    }

    // Marker <foreach>
    MarkerForeach markerForeach = asMarkerForeach(block, elements, elementIndex);
    if (markerForeach != null) {
      return markerForeach;
    }

    // Marker <when>
    MarkerWhen markerWhen = asMarkerWhen(block, elements, elementIndex);
    if (markerWhen != null) {
      return markerWhen;
    }

    // Marker <else>
    MarkerElse markerElse = asMarkerElse(block, elements, elementIndex);
    if (markerElse != null) {
      return markerElse;
    }

    // Marker <end>
    MarkerEnd markerEnd = asMarkerEnd(block, elements, elementIndex);
    if (markerEnd != null) {
      return markerEnd;
    }

    throw ParseTemplateException.withMessage("Invalid template marker at position {}:{}",
        block.position().row(), block.position().column());
  }

  private static MarkerPrint asMarkerPrint(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_PRINT_PATTERN.matcher(block.value());
    if (matcher.matches()) {
      return PrintMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .outputExpression(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .get();
    }
    return null;
  }

  private static MarkerPrint asShortMarkerPrint(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_PRINT_SHORT_PATTERN.matcher(block.value());
    if (matcher.matches()) {
      return PrintMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .outputExpression(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .get();
    }
    return null;
  }

  private static MarkerSet asMarkerSet(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_SET_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      return SetMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .valueName(matcher.group(1).trim())
          .valueExpression(ParseExpressionFunctions.parseExpression(matcher.group(2).trim()))
          .get();
    }
    return null;
  }

  private static MarkerFormat asMarkerFormat(
      Block block, List<TemplateElement> elements, int elementIndex
  )  {
    Matcher matcher = MARKER_FORMAT_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      return FormatMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .types(Arrays.stream(matcher.group(1).split(","))
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(MarkerFormatTypes::valueOf)
              .map(t -> (MarkerFormatType) t)
              .toList())
          .get();
    }
    return null;
  }

  private static MarkerForeach asMarkerForeach(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_FOREACH_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      return ForeachMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .itemName(matcher.group(1))
          .collectionExpression(ParseExpressionFunctions.parseExpression(matcher.group(2).trim()))
          .get();
    }
    return null;
  }

  private static MarkerWhen asMarkerWhen(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_WHEN_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      return WhenMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .condition(ParseExpressionFunctions.parseExpression(matcher.group(1).trim()))
          .get();
    }
    return null;
  }

  private static MarkerElse asMarkerElse(
      Block block, List<TemplateElement> elements, int elementIndex
  ) throws ParseTemplateException {
    Matcher matcher = MARKER_ELSE_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      String condition = matcher.group(2);
      return ElseMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .condition(condition != null ? ParseExpressionFunctions.parseExpression(condition.trim()) : null)
          .get();
    }
    return null;
  }

  private static MarkerEnd asMarkerEnd(
      Block block, List<TemplateElement> elements, int elementIndex
  ) {
    Matcher matcher = MARKER_END_PATTERN.matcher(block.wording());
    if (matcher.matches()) {
      return EndMarkers.build()
          .context(ElementContexts.build()
              .position(block.position())
              .templateElements(elements)
              .elementIndex(elementIndex)
              .get())
          .get();
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
              TextElements.build()
                  .context(ElementContexts.build()
                      .position(prevElement.context().position())
                      .templateElements(elements)
                      .get())
                  .text(SourceFunctions.removeLastGaps(((TextElement) prevElement).text()))
                  .get()
          );
        }
        if (ind < elements.size() - 1) {
          TemplateElement nextElement = result.get(ind + 1);
          result.set(ind + 1,
              TextElements.build()
                  .context(ElementContexts.build()
                      .position(nextElement.context().position())
                      .templateElements(elements)
                      .get())
                  .text(SourceFunctions.removeFirstBlanksAndLinebreak(((TextElement) nextElement).text()))
                  .get()
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

  private static boolean isOneLineHiddenElement(
      TemplateElement prevElement, TemplateElement curElement, TemplateElement nextElement
  ) {
    return (prevElement == null || isEndWithLineBreakIgnoreBlanks(prevElement))
        && isHiddenElement(curElement.type())
        && (nextElement == null || isBeginWithLineBreakIgnoreBlanks(nextElement));
  }

  private static boolean isEndWithLineBreakIgnoreBlanks(TemplateElement element) {
    return element.type() == TemplateElementTypes.Text &&
        SourceFunctions.isEndWithLinebreakIgnoreBlanks(((TextElement) element).text());
  }

  private static boolean isBeginWithLineBreakIgnoreBlanks(TemplateElement element) {
    return element.type() == TemplateElementTypes.Text &&
        SourceFunctions.isBeginWithLinebreakIgnoreBlanks(((TextElement) element).text());
  }

  private static boolean isHiddenElement(TemplateElementType elementType) {
    return TemplateElementTypes.MarkerSet == elementType
        || TemplateElementTypes.MarkerForeach == elementType
        || TemplateElementTypes.MarkerFormat == elementType
        || TemplateElementTypes.MarkerWhen == elementType
        || TemplateElementTypes.MarkerElse == elementType
        || TemplateElementTypes.MarkerEnd == elementType;
  }

  private static List<TemplateElement> analyzeStatements(
      List<TemplateElement> elements
  ) throws ParseTemplateException {
    return analyzeStatements(elements.listIterator(), elements);
  }

  private static List<TemplateElement> analyzeStatements(
      ListIterator<TemplateElement> iterator, List<TemplateElement> elements
  ) throws ParseTemplateException {
    List<TemplateElement> resultElements = new ArrayList<>();
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerFormat.equals(element.type())) {
        MarkerFormat markerFormat = (MarkerFormat) element;
        StatementFormat statementFormat = readFormatStatement(markerFormat, iterator, elements);
        resultElements.add(statementFormat);
      } else if (TemplateElementTypes.MarkerForeach.equals(element.type())) {
        MarkerForeach markerForeach = (MarkerForeach) element;
        StatementForeach statementForeach = readForeachStatement(markerForeach, iterator, elements);
        resultElements.add(statementForeach);
    } else if (TemplateElementTypes.MarkerWhen.equals(element.type())) {
        MarkerWhen markerWhen = (MarkerWhen) element;
        StatementWhen statementWhen = readWhenStatement(markerWhen, iterator, elements);
        resultElements.add(statementWhen);
      } else {
        resultElements.add(element);
      }
    }
    return resultElements;
  }

  private static StatementFormat readFormatStatement(
      MarkerFormat markerFormat, ListIterator<TemplateElement> iterator, List<TemplateElement> elements
  ) throws ParseTemplateException {
    List<TemplateElement> subElements = readUpToMarkerEnd(markerFormat, iterator);
    List<TemplateElement> subStatements = analyzeStatements(subElements);
    return FormatStatements.build()
        .context(ElementContexts.build()
            .position(markerFormat.context().position())
            .templateElements(elements)
            .get())
        .types(markerFormat.types())
        .subElements(subStatements)
        .get();
  }

  private static StatementForeach readForeachStatement(
      MarkerForeach markerForeach, ListIterator<TemplateElement> iterator, List<TemplateElement> elements
  ) throws ParseTemplateException {
    List<TemplateElement> subElements = readUpToMarkerEnd(markerForeach, iterator);
    List<TemplateElement> subStatements = analyzeStatements(subElements);
    return ForeachStatements.build()
        .context(ElementContexts.build()
            .position(markerForeach.context().position())
            .templateElements(elements)
            .get())
        .collectionExpression(markerForeach.collectionExpression())
        .itemName(markerForeach.itemName())
        .subElements(subStatements)
        .get();
  }

  private static StatementWhen readWhenStatement(
      MarkerWhen markerWhen, ListIterator<TemplateElement> iterator, List<TemplateElement> elements
  ) throws ParseTemplateException {
    List<StatementWhenBranch> branches = new ArrayList<>();
    StatementWhenBranch defaultBranch = null;

    List<TemplateElement> curBranchElements = readWhenBranch(markerWhen, iterator, elements);
    curBranchElements = analyzeStatements(curBranchElements);
    branches.add(WhenBranchStatements.build()
        .condition(markerWhen.condition())
        .subElements(curBranchElements)
        .get());
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerElse == element.type()) {
        MarkerElse markerElse = (MarkerElse) element;
        curBranchElements = readWhenBranch(element, iterator, elements);
        curBranchElements = analyzeStatements(curBranchElements);
        StatementWhenBranch branch = WhenBranchStatements.build()
            .condition(markerElse.condition())
            .subElements(curBranchElements)
            .get();
        if (markerElse.condition() == null) {
          defaultBranch = branch;
        } else {
          branches.add(branch);
        }
      } else if (TemplateElementTypes.MarkerEnd == element.type()) {
        break;
      } else {
        throw ParseTemplateException.withMessage("Unexpected element of type {} at position {}:{}. " +
                "Expected markers {{else}} or {{end}}",
            element.context().position().row(), element.context().position().column());
      }
    }
    return WhenStatements.build()
        .context(ElementContexts.build()
            .position(markerWhen.context().position())
            .templateElements(elements)
            .get())
        .branches(branches)
        .defaultBranch(defaultBranch)
        .get();
  }

  private static List<TemplateElement> readWhenBranch(
      TemplateElement conditionElement, ListIterator<TemplateElement> iterator, List<TemplateElement> elements
  ) throws ParseTemplateException {
    List<TemplateElement> branchElements = new ArrayList<>();
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
      if (TemplateElementTypes.MarkerElse == element.type() || TemplateElementTypes.MarkerEnd == element.type()) {
        iterator.previous();
        return branchElements;
      } else if (TemplateElementTypes.MarkerWhen == element.type()) {
        TemplateElement nestedWhen = readWhenStatement((MarkerWhen) element, iterator, elements);
        branchElements.add(nestedWhen);
      } else {
        branchElements.add(element);
      }
    }
    throw ParseTemplateException.withMessage("End marker missing for statement marker at position {}:{}",
        conditionElement.context().position().row(), conditionElement.context().position().column());
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
    throw ParseTemplateException.withMessage("End marker missing for statement marker at position {}:{}",
        startElement.context().position().row(), startElement.context().position().column());
  }

  private static boolean isStatementBegin(TemplateElement element) {
    return TemplateElementTypes.MarkerFormat == element.type()
        || TemplateElementTypes.MarkerWhen == element.type()
        || TemplateElementTypes.MarkerForeach == element.type();
  }

  private TemplateFunctions() {}

  private static final char OPEN_CURLY_BRACE = '{';
  private static final char CLOSE_CURLY_BRACE = '}';
  private static final String IDENTIFIER_CHARS = "\\w";
  private static final String EXPRESSION_CHARS = " \\t\\w\\.,:\\(\\)\\[\\]\"'";

  private static final Pattern MARKER_SET_PATTERN = Pattern.compile(
      "\\{\\{set[ \\t]+(\\w+)[ \\t]*=[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)\\}\\}");
  private static final Pattern MARKER_PRINT_PATTERN = Pattern.compile(
      "print[ \\t]+([\\$" + EXPRESSION_CHARS + "]+)");
  private static final Pattern MARKER_PRINT_SHORT_PATTERN = Pattern.compile(
      "[ \\t]*(\\$[" + IDENTIFIER_CHARS + "]+[" + EXPRESSION_CHARS + "]*)[ \\t]*");
  private static final Pattern MARKER_FORMAT_PATTERN = Pattern.compile(
      "\\{\\{format[ \\t]+([\\w, \\t]+)\\}\\}");
  private static final Pattern MARKER_FOREACH_PATTERN = Pattern.compile(
      "\\{\\{for[ \\t]+([" + IDENTIFIER_CHARS + "]+)[ \\t]*:[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)[ \\t]*\\}\\}");
  private static final Pattern MARKER_WHEN_PATTERN = Pattern.compile(
      "\\{\\{when[ \\t]+([\\$" + EXPRESSION_CHARS + "]+)\\}\\}");
  private static final Pattern MARKER_ELSE_PATTERN = Pattern.compile(
      "\\{\\{else[ \\t]*(when[ \\t]+([\\$" + EXPRESSION_CHARS + "]+))?\\}\\}");
  private static final Pattern MARKER_END_PATTERN = Pattern.compile(
      "\\{\\{end[ \\t\\w]*\\}\\}");
}
