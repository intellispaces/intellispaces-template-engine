package tech.intellispaces.commons.templateengine.element;

public interface ForeachStatements {

  static ForeachStatementBuilder build() {
    return new ForeachStatementBuilder();
  }
}
