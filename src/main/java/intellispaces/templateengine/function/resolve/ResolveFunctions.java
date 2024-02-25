package intellispaces.templateengine.function.resolve;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.cast.CastFunctions;
import intellispaces.templateengine.model.element.StatementWhenBranch;
import intellispaces.templateengine.object.element.MarkerFormatTypes;
import intellispaces.templateengine.model.TextTemplate;
import intellispaces.templateengine.model.element.MarkerForeach;
import intellispaces.templateengine.model.element.StatementForeach;
import intellispaces.templateengine.model.element.MarkerFormat;
import intellispaces.templateengine.model.element.StatementFormat;
import intellispaces.templateengine.model.element.MarkerWhen;
import intellispaces.templateengine.model.element.StatementWhen;
import intellispaces.templateengine.model.element.MarkerElse;
import intellispaces.templateengine.model.element.MarkerEnd;
import intellispaces.templateengine.model.element.MarkerPrint;
import intellispaces.templateengine.model.element.MarkerSet;
import intellispaces.templateengine.model.element.TemplateElement;
import intellispaces.templateengine.model.element.TextElement;
import intellispaces.templateengine.model.value.Value;
import intellispaces.templateengine.object.value.ItemValueBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ResolveFunctions {

  static String resolveTemplate(TextTemplate template, Map<String, Object> variables) throws ResolveTemplateException {
    var values = new HashMap<String, Value>();
    for (Map.Entry<String, Object> entry : variables.entrySet()) {
      values.put(entry.getKey(), CastFunctions.objectToValue(entry.getValue()));
    }

    var sb = new StringBuilder();
    for (TemplateElement element : template.elements()) {
      sb.append(element.resolve(values));
    }
    return sb.toString();
  }


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
    var subParams = new HashMap<>(variables);
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
