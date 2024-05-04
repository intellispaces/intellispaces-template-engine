package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.source.position.Position;

import java.util.List;
import java.util.Objects;

public final class StatementForeachBuilder {
  private Position position;
  private Expression collectionExpression;
  private String itemName;
  private List<TemplateElement> subElements;

  public static StatementForeachBuilder get() {
    return new StatementForeachBuilder();
  }

  public StatementForeachBuilder position(Position position) {
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
    return new StatementForeachImpl(position, collectionExpression, itemName, subElements);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(collectionExpression);
    Objects.requireNonNull(itemName);
    Objects.requireNonNull(subElements);
  }

  private StatementForeachBuilder() {}
}
