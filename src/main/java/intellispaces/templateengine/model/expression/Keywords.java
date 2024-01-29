package intellispaces.templateengine.model.expression;

/**
 * Template keywords.
 */
public enum Keywords implements Keyword {

  True("true"),

  False("false"),

  Null("null");

  private final String wording;

  Keywords(String wording) {
    this.wording = wording;
  }

  @Override
  public String wording() {
    return wording;
  }
}
