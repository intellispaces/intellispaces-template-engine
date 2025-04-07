package tech.intellispaces.templateengine.expression;

public interface Expressions {

  static ExpressionBuilder build() {
    return new ExpressionBuilder();
  }
}
