package tech.intellispaces.templates.expression;

public interface Expressions {

  static ExpressionBuilder build() {
    return new ExpressionBuilder();
  }
}
