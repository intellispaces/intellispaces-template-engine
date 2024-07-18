package tech.intellispaces.framework.templateengine.template.element;

public interface WhenStatements {

  static WhenStatementBuilder build() {
    return new WhenStatementBuilder();
  }
}
