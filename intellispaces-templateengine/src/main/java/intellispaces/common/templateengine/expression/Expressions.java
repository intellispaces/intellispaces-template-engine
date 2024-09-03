package intellispaces.common.templateengine.expression;

public interface Expressions {

  static ExpressionBuilder build() {
    return new ExpressionBuilder();
  }
}
