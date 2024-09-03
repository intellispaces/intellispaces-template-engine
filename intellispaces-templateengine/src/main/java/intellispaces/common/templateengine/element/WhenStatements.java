package intellispaces.common.templateengine.element;

public interface WhenStatements {

  static WhenStatementBuilder build() {
    return new WhenStatementBuilder();
  }
}
