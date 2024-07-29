package tech.intellispaces.templates.template.expression;

/**
 * Expression keywords.
 */
public enum Keywords implements Keyword {

  True("true"),

  False("false"),

  Void("void");

  private final String word;

  Keywords(String word) {
    this.word = word;
  }

  @Override
  public String word() {
    return word;
  }
}
