package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.StatementForeach;
import intellispaces.templateengine.model.element.TemplateElement;
import intellispaces.templateengine.model.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class StatementForeachBuilder {
  private TextPosition position;
  private Expression collectionExpression;
  private String itemName;
  private List<TemplateElement> subElements;

  public static StatementForeachBuilder get() {
    return new StatementForeachBuilder();
  }

  public StatementForeachBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public StatementForeachBuilder collectionExpression(Expression collectionExpression) {
    this.collectionExpression = collectionExpression;
    return this;
  }

  public StatementForeachBuilder itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public StatementForeachBuilder subElements(List<TemplateElement> subElements) {
    this.subElements = subElements;
    return this;
  }

  public StatementForeach build() {
    validate();
    return new StatementForeachObject(position, collectionExpression, itemName, subElements);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
    Objects.requireNonNull(subElements);
  }

  private StatementForeachBuilder() {}
}
