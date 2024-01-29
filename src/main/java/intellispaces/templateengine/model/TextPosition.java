package intellispaces.templateengine.model;

/**
 * Position inside template source.
 */
public interface TextPosition {

  /**
   * Offset from the beginning of the template source.
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
