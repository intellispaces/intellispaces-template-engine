package intellispaces.common.templateengine.source.block;

import intellispaces.common.templateengine.source.position.Position;

/**
 * Template source block.
 */
public interface Block {

  /**
   * Position of the block in template source.
   */
  Position position();

  /**
   * Block length, characters.
   */
  int length();

  /**
   * Marker block indicator.
   *
   * <p>Marker block starts with open double curly braces <code>{{</code> and finishes with close double curly braces <code>}}</code>.
   */
  boolean isMarker();

  /**
   * Block wording.
   */
  String wording();

  /**
   * Block value.
   *
   * <p>For marker blocks the value is wording between starting <code>{{</code> and finishing <code>}}</code>.
   * For other blocks value is full text of the block and value equals to <code>this.text()</code>.
   */
  String value();
}
