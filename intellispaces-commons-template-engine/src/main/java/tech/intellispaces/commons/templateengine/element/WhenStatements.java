package tech.intellispaces.commons.templateengine.element;

public interface WhenStatements {

  static WhenStatementBuilder build() {
    return new WhenStatementBuilder();
  }
}
