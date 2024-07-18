package tech.intellispaces.framework.templateengine.template.element;

public interface WhenStatements {

  static WhenStatementBuilder get() {
    return new WhenStatementBuilder();
  }
}
