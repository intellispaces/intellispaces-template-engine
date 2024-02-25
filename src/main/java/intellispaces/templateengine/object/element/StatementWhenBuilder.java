package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.StatementWhen;
import intellispaces.templateengine.model.element.StatementWhenBranch;

import java.util.List;
import java.util.Objects;

public final class StatementWhenBuilder {
  private TextPosition position;
  private List<StatementWhenBranch> branches;
  private StatementWhenBranch defaultBranch;

  public static StatementWhenBuilder get() {
    return new StatementWhenBuilder();
  }

  public StatementWhenBuilder position(TextPosition position) {
    this.position = position;
    return this;
  }

  public StatementWhenBuilder branches(List<StatementWhenBranch> branches) {
    this.branches = branches;
    return this;
  }

  public StatementWhenBuilder defaultBranch(StatementWhenBranch defaultBranch) {
    this.defaultBranch = defaultBranch;
    return this;
  }

  public StatementWhen build() {
    validate();
    return new StatementWhenObject(position, branches, defaultBranch);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(branches);
  }

  private StatementWhenBuilder() {}
}
