package intellispaces.common.templateengine.element;

public interface WhenBranchStatements {

  static WhenBranchStatementBuilder build() {
    return new WhenBranchStatementBuilder();
  }
}
