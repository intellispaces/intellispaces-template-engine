package tech.intellispaces.templateengine.element;

public interface WhenBranchStatements {

  static WhenBranchStatementBuilder build() {
    return new WhenBranchStatementBuilder();
  }
}
