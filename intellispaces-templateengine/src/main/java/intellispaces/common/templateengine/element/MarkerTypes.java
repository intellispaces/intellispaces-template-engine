package intellispaces.common.templateengine.element;

/**
 * Template marker types.
 */
public enum MarkerTypes implements MarkerType {

  /**
   * Marker IF.
   */
  If("{{if}}", "if"),

  /**
   * Marker ELSE.
   */
  Else("{{else}}", "else"),

  /**
   * Marker END.
   */
  End("{{end}}", "end");

  private final String text;
  private final String value;

  MarkerTypes(String text, String value) {
    this.text = text;
    this.value = value;
  }

  @Override
  public String text() {
    return text;
  }

  @Override
  public String value() {
    return value;
  }
}
