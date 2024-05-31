package tech.intellispaces.framework.templateengine.template.source.position;

/**
 * Position inside template text.
 */
public interface Position {

  /**
   * Offset from the beginning of the template.
   */
  int offset();

  /**
   * Row number. The first row number is 1.
   */
  int row();

  /**
   * Column number. The first column number is 1.
   */
  int column();
}
