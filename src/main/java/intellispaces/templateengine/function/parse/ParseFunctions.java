package intellispaces.templateengine.function.parse;

import intellispaces.templateengine.builder.TextBlockBuilder;
import intellispaces.templateengine.builder.TextPositionBuilder;
import intellispaces.templateengine.builder.TextTemplateBuilder;
import intellispaces.templateengine.builder.element.MarkerElseBuilder;
import intellispaces.templateengine.builder.element.MarkerEndBuilder;
import intellispaces.templateengine.builder.element.TextElementBuilder;
import intellispaces.templateengine.exception.ParseTemplateException;
import intellispaces.templateengine.model.TextBlock;
import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.MutableTextPosition;
import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.element.MarkerIf;
import intellispaces.templateengine.model.element.MarkerEnd;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.MarkerTypes;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.element.TemplateElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class ParseFunctions {

  /**
   * Parse template.
   *
   * @param source template source.
   * @return parsed template.
   * @throws ParseTemplateException throws when template can't be parsed.
   */
  public static TextTemplate parse(String source) throws ParseTemplateException {
    return TextTemplateBuilder.build(analyzeStatements(analyzeElements(splitByMarkers(source))));
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

  static boolean isOpenDoubleCurlyBraces(char[] source, int offset) {
    return offset + 1 < source.length && source[offset] == OPEN_CURLY_BRACE && source[offset + 1] == OPEN_CURLY_BRACE;
  }

  static boolean isCloseDoubleCurlyBraces(char[] source, int offset) {
    return offset + 1 < source.length && source[offset] == CLOSE_CURLY_BRACE && source[offset + 1] == CLOSE_CURLY_BRACE;
  }

  static String readElementValue(boolean marker, char[] source, int offset, int numChars) {
    if (marker) {
      // Exclude start and finish double curly braces
      return makeString(source, offset + 2, numChars - 4);
    } else {
      return makeString(source, offset, numChars);
    }
  }

  static String makeString(char[] source, int position, int numChars) {
    return new String(source, position, numChars);
  }

  static void movePosition(MutableTextPosition position, TextBlock block, char[] source) {
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
  static List<TemplateElement> analyzeElements(List<TextBlock> blocks) throws ParseTemplateException {
    var elements = new ArrayList<TemplateElement>(blocks.size());
    for (var block : blocks) {
      if (block.isMarker()) {
        TemplateElement element = analyzeMarker(block);
        elements.add(element);
      } else {
        elements.add(TextElementBuilder.get()
            .position(block.position())
            .text(block.text())
            .build());
      }
    }

//    int start = 0;
//    int cutChars = 0;
//    for (var block : blocks) {
//      TemplateElement element = analyzeMarker(block);
//      if (start != element.start()) {
//        elements.add(Builders.newTextStatement()
//            .withStart(start + cutChars)
//            .withEnd(block.start() + cutChars)
//            .withText(source.substring(start + cutChars, block.start()))
//            .get()
//        );
//      }
//      start = block.end();
//
//      if (!StatementKinds.Text.equals(element.type()) && !StatementKinds.Print.equals(element.type())) {
//        cutChars = calcCuttingChars(source, block);
//      } else if (cutChars != 0) {
//        element = Builders.newTextStatement()
//            .withStart(element.start() + cutChars)
//            .withEnd(element.end())
//            .withText(element.text().substring(cutChars))
//            .get();
//        cutChars = 0;
//      }
//      elements.add(element);
//    }
//    if (start != source.length()) {
//      elements.add(Builders.newTextStatement()
//          .withStart(start + cutChars)
//          .withEnd(source.length())
//          .withText(source.substring(start + cutChars))
//          .get()
//      );
//    }
    return elements;
  }

  static int calcCuttingChars(String source, TextBlock block) {
    var cutChars = 0;
//    var blockStart = block.start();
//    var blockEnd = block.end();
//    if (blockStart == 0 || source.charAt(blockStart - 1) == '\n') {
//      if (blockEnd < source.length() && source.charAt(blockEnd) == '\n') {
//        cutChars = 1;
//      } else if (blockEnd + 1 < source.length() && source.charAt(blockEnd) == '\r' && source.charAt(blockEnd + 1) == '\n') {
//        cutChars = 2;
//      }
//    }
    return cutChars;
  }

  static TemplateElement analyzeMarker(TextBlock marker) throws ParseTemplateException {
    // Marker "print"
    var markerPrint = asMarkerPrint(marker);
    if (markerPrint != null) {
      return markerPrint;
    }

    // Short marker "print"
    var shortMarkerPrint = asShortMarkerPrint(marker);
    if (shortMarkerPrint != null) {
      return shortMarkerPrint;
    }

    // Set statement
    var markerSet = asMarkerSet(marker);
    if (markerSet != null) {
      return markerSet;
    }

    // Marker "If"
    var markerIf = asMarkerIf(marker);
    if (markerIf != null) {
      return markerIf;
    }

    // Marker "else"
    var markerElse = asMarkerElse(marker);
    if (markerElse != null) {
      return markerElse;
    }

    // Marker "foreach"
    var markerForeach = asMarkerForeach(marker);
    if (markerForeach != null) {
      return markerForeach;
    }

    // Marker "end"
    var markerEnd = asMarkerEnd(marker);
    if (markerEnd != null) {
      return markerEnd;
    }

    // Marker "format"
    var markerFormat = asMarkerFormat(marker);
    if (markerFormat != null) {
      return markerFormat;
    }

    throw new ParseTemplateException("Invalid template marker at position {0}:{1}", marker.position().row(), marker.position().column());
  }

  static MarkerPrint asMarkerPrint(TextBlock block) throws ParseTemplateException {
//    var matcher = MARKET_PRINT_PATTERN.matcher(block.value());
//    if (matcher.matches()) {
//      return Builders.newPrintStatement()
//          .withStart(block.start())
//          .withEnd(block.end())
//          .withText(matcher.group(0))
//          .withOutputExpression(
//              Builders.newExpressionStatement(matcher.start(1), matcher.end(1), matcher.group(1))
//                  .withExpression(ParseExpression.parse(matcher.group(1)))
//                  .get()
//          ).get();
//    }
    return null;
  }

  static MarkerPrint asShortMarkerPrint(TextBlock block) throws ParseTemplateException {
//    var matcher = MARKER_SHORT_PRINT_PATTERN.matcher(block.value());
//    if (matcher.matches()) {
//      return Builders.newPrintStatement()
//          .withStart(block.start())
//          .withEnd(block.end())
//          .withText(matcher.group(0))
//          .withOutputExpression(
//              Builders.newExpressionStatement(matcher.start(1), matcher.end(1), matcher.group(1))
//                  .withExpression(ParseExpression.parse(matcher.group(1)))
//                  .get()
//          ).get();
//    }
    return null;
  }

  static MarkerSet asMarkerSet(TextBlock marker) throws ParseTemplateException {
//    var matcher = SET_BLOCK_PATTERN.matcher(block.text());
//    if (matcher.matches()) {
//      return Builders.newSetStatement()
//          .withStart(block.start())
//          .withEnd(block.end())
//          .withText(matcher.group(0))
//          .withValueName(matcher.group(1))
//          .withValueExpression(
//              Builders.newExpressionStatement(matcher.start(2), matcher.end(2), matcher.group(2))
//                  .withExpression(ParseExpression.parse(matcher.group(2)))
//                  .get()
//          ).get();
//    }
    return null;
  }

  static MarkerForeach asMarkerForeach(TextBlock marker) throws ParseTemplateException {
//    var matcher = FOREACH_HEADER_PATTERN.matcher(block.text());
//    if (matcher.matches()) {
//      return Builders.newForeachHeaderStatement(block.start(), block.end(), matcher.group(0))
//          .withCollectionExpression(
//              Builders.newExpressionStatement(matcher.start(2), matcher.end(2), matcher.group(2))
//                  .withExpression(ParseExpression.parse(matcher.group(2)))
//                  .get()
//          )
//          .withItemName(matcher.group(1))
//          .get();
//    }
    return null;
  }

  static MarkerIf asMarkerIf(TextBlock marker) throws ParseTemplateException {
//    var matcher = IF_HEADER_PATTERN.matcher(block.text());
//    if (matcher.matches()) {
//      return Builders.newIfHeaderStatement()
//          .withStart(block.start())
//          .withEnd(block.end())
//          .withText(matcher.group(0))
//          .withConditionExpression(
//              Builders.newExpressionStatement(matcher.start(1), matcher.end(1), matcher.group(1))
//                  .withExpression(ParseExpression.parse(matcher.group(1)))
//                  .get()
//          ).get();
//    }
    return null;
  }

  static MarkerElse asMarkerElse(TextBlock marker) {
    if (MarkerTypes.Else.value().equals(marker.value())) {
      return MarkerElseBuilder.get()
          .position(marker.position())
          .build();
    }
    return null;
  }

  static MarkerFormat asMarkerFormat(TextBlock marker)  {
//    var matcher = FORMAT_HEADER_PATTERN.matcher(block.text());
//    if (matcher.matches()) {
//      return Builders.newFormatHeaderStatement()
//          .withStart(block.start())
//          .withEnd(block.end())
//          .withText(block.text())
//          .withTypes(Arrays.stream(matcher.group(1).split(",")).map(String::trim).map(String::toUpperCase).collect(Collectors.toList()))
//          .get();
//    }
    return null;
  }

  static MarkerEnd asMarkerEnd(TextBlock marker) {
    if (MarkerTypes.End.value().equals(marker.value())) {
      return MarkerEndBuilder.get()
          .position(marker.position())
          .build();
    }
    return null;
  }

  static List<TemplateElement> analyzeStatements(List<TemplateElement> elements) throws ParseTemplateException {
    var resultElements = new ArrayList<TemplateElement>();
    Iterator<TemplateElement> iterator = elements.iterator();
    while (iterator.hasNext()) {
      TemplateElement element = iterator.next();
//      if (TemplateElementTypes.MarkerForeach.equals(element.type())) {
//        templateBlocks.add(makeForeachStatement(analyzeStatements(element, iterator)));
//      } else if (TemplateElementTypes.MarkerIf.equals(element.type())) {
//        templateBlocks.add(makeIfStatement(analyzeStatements(element, iterator)));
//      } else if (TemplateElementTypes.MarkerFormat.equals(element.type())) {
//        templateBlocks.add(makeFormatStatement(analyzeStatements(element, iterator)));
//      } else {
      resultElements.add(element);
//      }
    }
    return resultElements;
  }

  static List<TemplateElement> analyzeStatements(TemplateElement headStatement, Iterator<TemplateElement> statementIterator) throws ParseTemplateException {
    var elements = new ArrayList<TemplateElement>();
    elements.add(headStatement);

//    while (statementIterator.hasNext()) {
//      var block = statementIterator.next();
//      if (StatementKinds.ForeachHeader.equals(block.type())) {
//        elements.add(makeForeachStatement(analyzeStatements(block, statementIterator)));
//      } else if (StatementKinds.IfHeader.equals(block.type())) {
//        elements.add(makeWhenStatement(analyzeStatements(block, statementIterator)));
//      } else if (StatementKinds.FormatHeader.equals(block.type())) {
//        elements.add(makeFormatStatement(analyzeStatements(block, statementIterator)));
//      } else if (StatementKinds.End.equals(block.type())) {
//        elements.add(block);
//        return elements;
//      } else {
//        elements.add(block);
//      }
//    }
    throw new ParseTemplateException("Block {} without the {{END}} block", headStatement);
  }


//  static ForeachStatement makeForeachStatement(List<TemplateElement> subStatements) {
//    var headerBlock = (ForeachHeaderStatement) subStatements.get(0);
//    var endBlock = (EndStatement) subStatements.get(subStatements.size() - 1);
//    return Builders.newForeachStatement()
//        .withStart(headerBlock.start())
//        .withEnd(endBlock.end())
//        .withText(subStatements.stream().map(Statement::text).collect(Collectors.joining()))
//        .withCollectionExpression(headerBlock.collectionExpression())
//        .withItemName(headerBlock.itemName())
//        .withSubStatements(subStatements.subList(1, subStatements.size() - 1))
//        .get();
//  }

//  static IfStatement makeWhenStatement(List<Statement> subStatements) {
//    var headerBlock = (IfHeaderStatement) subStatements.get(0);
//    var endBlock = (EndStatement) subStatements.get(subStatements.size() - 1);
//
//    var branch = new IfStatement.Branch(headerBlock.conditionExpression(), new ArrayList<>());
//    var otherwiseBlocks = new ArrayList<Statement>();
//    List<Statement> curStatements = branch.subStatements();
//    for (var subBlock : subStatements) {
//      if (StatementKinds.IfHeader.equals(subBlock.type())) {
//        // skip
//      } else if (StatementKinds.Else.equals(subBlock.type())) {
//        curStatements = otherwiseBlocks;
//      } else if (StatementKinds.End.equals(subBlock.type())) {
//        break;
//      } else {
//        curStatements.add(subBlock);
//      }
//    }
//    return Builders.newIfStatement()
//        .withStart(headerBlock.start())
//        .withEnd(endBlock.end())
//        .withText(subStatements.stream().map(Statement::text).collect(Collectors.joining()))
//        .withBranches(List.of(branch))
//        .withOtherwiseStatements(otherwiseBlocks)
//        .get();
//  }

//  static FormatStatement makeFormatStatement(List<TemplateElement> subStatements) {
//    var headerBlock = (FormatHeaderStatement) subStatements.get(0);
//    var endBlock = (EndStatement) subStatements.get(subStatements.size() - 1);
//    return Builders.newFormatStatement()
//        .withStart(headerBlock.start())
//        .withEnd(endBlock.end())
//        .withText(subStatements.stream().map(Statement::text).collect(Collectors.joining()))
//        .withTypes(headerBlock.types())
//        .withSubStatements(subStatements.subList(1, subStatements.size() - 1))
//        .get();
//  }

  private ParseFunctions() {}

  private static final char OPEN_CURLY_BRACE = '{';
  private static final char CLOSE_CURLY_BRACE = '}';
  private static final String EXPRESSION_CHARS = " \\t\\w\\.\\(\\)\\[\\]\"'";
  private static final Pattern END_PATTERN = Pattern.compile("\\{\\{END[ \\t\\w]*\\}\\}");
  private static final Pattern FORMAT_HEADER_PATTERN = Pattern.compile("\\{\\{FORMAT[ \\t]+([\\w, \\t]+)\\}\\}");
  private static final Pattern FOREACH_HEADER_PATTERN = Pattern.compile("\\{\\{FOR[ \\t]+(\\w+)[ \\t]*:[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)[ \\t]*\\}\\}");
  private static final Pattern IF_HEADER_PATTERN = Pattern.compile("\\{\\{IF +([\\$" + EXPRESSION_CHARS + "\\$" + "]+)\\}\\}");
  private static final Pattern SET_BLOCK_PATTERN = Pattern.compile("\\{\\{SET[ \\t]+(\\w+)[ \\t]*=[ \\t]*([\\$" + EXPRESSION_CHARS + "]+)\\}\\}");
  private static final Pattern MARKET_PRINT_PATTERN = Pattern.compile("print[ \\t]+([\\$" + EXPRESSION_CHARS + "]+)");
  private static final Pattern MARKER_SHORT_PRINT_PATTERN = Pattern.compile("[ \\t]*(\\$[" + EXPRESSION_CHARS + "]+)");
}
