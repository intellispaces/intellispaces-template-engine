package tech.intellispaces.templateengine.element;

public interface WhenStatements {

  static WhenStatementBuilder build() {
    return new WhenStatementBuilder();
  }
}
