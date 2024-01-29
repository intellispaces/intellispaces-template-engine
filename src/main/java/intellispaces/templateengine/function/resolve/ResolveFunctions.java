package intellispaces.templateengine.function.resolve;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.element.StatementForeach;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.StatementFormat;
import intellispaces.templateengine.model.element.MarkerIf;
import intellispaces.templateengine.model.element.StatementIf;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.element.MarkerEnd;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.element.TemplateElement;
import intellispaces.templateengine.model.element.TextElement;
import intellispaces.templateengine.model.value.Value;

import java.util.HashMap;
import java.util.Map;

public interface ResolveFunctions {

  static String resolve(TextTemplate template, Map<String, Object> params) throws ResolveTemplateException {
    var values = new HashMap<String, Value>();
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      values.put(entry.getKey(), CastFunctions.castToValue(entry.getValue()));
    }

    var sb = new StringBuilder();
    for (TemplateElement element : template.elements()) {
      sb.append(element.resolve(values));
    }
    return sb.toString();
  }

  static String resolve(TextElement element, Map<String, Value> params) {
    return element.text();
  }

  static String resolve(MarkerPrint statement, Map<String, Value> params) throws ResolveTemplateException {
//    return ResolveExpression.resolveExpressionToString(statement.outputExpression(), params);
    return null;
  }

  static String resolve(MarkerSet statement, Map<String, Value> params) throws ResolveTemplateException {
//    var value = ResolveExpression.resolveExpression(statement.valueExpression(), params);
//    params.put(statement.valueName(), value);
    return "";
  }

  static String resolve(StatementForeach statement, Map<String, Value> params) throws ResolveTemplateException {
    var sb = new StringBuilder();
//    var subParams = new HashMap<>(params);
//    var iterable = (Iterable<Object>) ResolveExpression.resolveExpressionToCollection(statement.collectionExpression(), params);
//    if (iterable != null) {
//      var index = 0;
//      var iterator = iterable.iterator();
//      while (iterator.hasNext()) {
//        subParams.put(
//            statement.itemName(),
//            Builders.newItemValue()
//                .withValue(CastObject.castToValue(iterator.next()))
//                .withIndex(Builders.newIntegerValue(index))
//                .withFirst(Builders.newBooleanValue(index == 0))
//                .withLast(Builders.newBooleanValue(!iterator.hasNext()))
//                .get()
//        );
//        index++;
//        for (var subBlock : statement.subStatements()) {
//          sb.append(subBlock.resolve(subParams));
//        }
//      }
//    }
    return sb.toString();
  }

  static String resolve(StatementIf statement, Map<String, Value> params) throws ResolveTemplateException {
    var sb = new StringBuilder();
//    for (var branch : statement.conditionBranches()) {
//      if (ResolveExpression.resolveExpressionToBoolean(branch.conditionExpression(), params)) {
//        for (var subBlock : branch.subStatements()) {
//          sb.append(resolveStatement(subBlock, params));
//        }
//        return sb.toString();
//      }
//    }
//    for (var subBlock : statement.otherwiseBranch()) {
//      sb.append(resolveStatement(subBlock, params));
//    }
    return sb.toString();
  }

  static String resolve(StatementFormat statement, Map<String, Value> params) throws ResolveTemplateException {
    var sb = new StringBuilder();
    for (var subBlock : statement.subStatements()) {
      sb.append(resolve(subBlock, params));
    }

    String text = sb.toString();
    if (statement.types().contains("NOBR")) {
      text = text.replaceAll("[\\n\\r]+", "");
    }
    return text;
  }

  static String resolve(MarkerElse element, Map<String, Value> params) {
    return "";
  }

  static String resolve(MarkerEnd element, Map<String, Value> params) {
    return "";
  }

  static String resolve(MarkerForeach element, Map<String, Value> params) {
    return "";
  }

  static String resolve(MarkerIf element, Map<String, Value> params) {
    return "";
  }

  static String resolve(MarkerFormat element, Map<String, Value> params) {
    return "";
  }

  static String resolve(TemplateElement element, Map<String, Value> params) throws ResolveTemplateException {
    return element.resolve(params);
  }
}
