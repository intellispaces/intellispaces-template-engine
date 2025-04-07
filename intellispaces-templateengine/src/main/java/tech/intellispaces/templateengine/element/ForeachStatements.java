package tech.intellispaces.templateengine.element;

public interface ForeachStatements {

  static ForeachStatementBuilder build() {
    return new ForeachStatementBuilder();
  }
}
