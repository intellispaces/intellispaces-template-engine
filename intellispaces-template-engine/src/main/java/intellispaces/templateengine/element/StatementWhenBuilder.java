package intellispaces.templateengine.element;

import intellispaces.templateengine.position.Position;

import java.util.List;
import java.util.Objects;

public final class StatementWhenBuilder {
  private Position position;
  private List<StatementWhenBranch> branches;
  private StatementWhenBranch defaultBranch;

  public static StatementWhenBuilder get() {
    return new StatementWhenBuilder();
  }

  public StatementWhenBuilder position(Position position) {
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
    return new StatementWhenImpl(position, branches, defaultBranch);
  }

  private void validate() {
    Objects.requireNonNull(position);
    Objects.requireNonNull(branches);
  }

  private StatementWhenBuilder() {}
}
