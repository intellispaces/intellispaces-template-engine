package intellispaces.templateengine.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.expression.value.ItemValueBuilder;
import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.expression.ResolveExpressionFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ResolveElementFunctions {

  static String resolve(TextElement element, Map<String, Value> variables) {
    return element.text();
  }

  static String resolve(MarkerPrint marker, Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveExpressionFunctions.resolveExpressionToString(marker.outputExpression(), variables);
  }

  static String resolve(MarkerSet marker, Map<String, Value> variables) throws ResolveTemplateException {
    Value value = ResolveExpressionFunctions.resolveExpression(marker.valueExpression(), variables);
    variables.put(marker.valueName(), value);
    return "";
  }

  static String resolve(StatementFormat statement, Map<String, Value> variables) throws ResolveTemplateException {
    var sb = new StringBuilder();
    List<TemplateElement> elements = statement.subElements();
    for (TemplateElement element : elements) {
      sb.append(resolve(element, variables));
    }

    String text = sb.toString();
    if (statement.types().contains(MarkerFormatTypes.nobr)) {
      text = text.replaceAll("[\\n\\r]+", "");
    }
    return text;
  }

  static String resolve(StatementForeach statement, Map<String, Value> variables) throws ResolveTemplateException {
    var sb = new StringBuilder();
    Map<String, Value> subParams = new HashMap<>(variables);
    List<Value> values = ResolveExpressionFunctions.resolveExpressionToList(statement.collectionExpression(), variables);
    if (values != null) {
      int index = 0;
      for (Value value : values) {
        subParams.put(
            statement.itemName(),
            ItemValueBuilder.get()
                .value(value)
                .index(index)
                .first(index == 0)
                .last(index == values.size() - 1)
                .build()
        );
        index++;

        for (TemplateElement element : statement.subElements()) {
          sb.append(element.resolve(subParams));
        }
      }
    }
    return sb.toString();
  }

  static String resolve(StatementWhen statement, Map<String, Value> variables) throws ResolveTemplateException {
    var sb = new StringBuilder();
    for (StatementWhenBranch branch : statement.branches()) {
      if (ResolveExpressionFunctions.resolveExpressionToBoolean(branch.condition(), variables)) {
        for (TemplateElement element : branch.subElements()) {
          sb.append(resolve(element, variables));
        }
        return sb.toString();
      }
    }
    if (statement.defaultBranch() != null) {
      for (TemplateElement element : statement.defaultBranch().subElements()) {
        sb.append(resolve(element, variables));
      }
    }
    return sb.toString();
  }

  static String resolve(MarkerElse element, Map<String, Value> variables) {
    return "";
  }

  static String resolve(MarkerEnd element, Map<String, Value> variables) {
    return "";
  }

  static String resolve(MarkerForeach element, Map<String, Value> variables) {
    return "";
  }

  static String resolve(MarkerWhen element, Map<String, Value> variables) {
    return "";
  }

  static String resolve(MarkerFormat element, Map<String, Value> variables) {
    return "";
  }

  static String resolve(TemplateElement element, Map<String, Value> variables) throws ResolveTemplateException {
    return element.resolve(variables);
  }
}
