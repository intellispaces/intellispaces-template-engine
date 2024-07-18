package tech.intellispaces.framework.templateengine.template.element;

public interface ForeachStatements {

  static ForeachStatementBuilder build() {
    return new ForeachStatementBuilder();
  }
}
