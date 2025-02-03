package tech.intellispaces.commons.templateengine.element;

public interface WhenBranchStatements {

  static WhenBranchStatementBuilder build() {
    return new WhenBranchStatementBuilder();
  }
}
