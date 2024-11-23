package tech.intellispaces.templateengine.element;

import tech.intellispaces.entity.text.StringFunctions;
import tech.intellispaces.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.templateengine.expression.ResolveExpressionFunctions;
import tech.intellispaces.templateengine.expression.value.ItemValues;
import tech.intellispaces.templateengine.expression.value.Value;
import tech.intellispaces.templateengine.source.SourceFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Functions to resolve template element to string representation.
 */
public interface ElementFunctions {

  static String resolve(TextElement element, Map<String, Value> variables) {
    return element.text();
  }

  static String resolve(MarkerPrint marker, Map<String, Value> variables) throws ResolveTemplateException {
    String value = ResolveExpressionFunctions.resolveExpressionToString(marker.outputExpression(), variables);

    int elementIndex = marker.context().elementIndex();
    if (elementIndex > 0) {
      TemplateElement prevElement = marker.context().templateElements().get(elementIndex - 1);
      if (prevElement.type() == TemplateElementTypes.Text) {
        String tail = SourceFunctions.getTailBeforeLinebreak(((TextElement) prevElement).text());
        if (!tail.isEmpty()) {
          String gap = StringFunctions.createBlankString(tail.length());
          String[] rows = value.split("\n");

          var sb = new StringBuilder();
          for (int ind = 0; ind < rows.length; ind++) {
            if (ind != 0) {
              sb.append(gap);
            }
            sb.append(rows[ind]);
            if (ind != rows.length - 1) {
              sb.append("\n");
            }
          }
          return sb.toString();
        }
      }
    }
    return value;
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
            ItemValues.build()
                .value(value)
                .index(index)
                .first(index == 0)
                .last(index == values.size() - 1)
                .get()
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
