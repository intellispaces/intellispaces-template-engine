package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.TextPosition;
import intellispaces.templateengine.model.element.StatementWhen;
import intellispaces.templateengine.model.element.StatementWhenBranch;

import java.util.List;

final class StatementWhenObject implements StatementWhen {
  private final TextPosition position;
  private final List<StatementWhenBranch> branches;
  private final StatementWhenBranch defaultBranch;

  StatementWhenObject(TextPosition position, List<StatementWhenBranch> branches, StatementWhenBranch defaultBranch) {
    this.position = position;
    this.branches = branches;
    this.defaultBranch = defaultBranch;
  }

  @Override
  public TextPosition position() {
    return position;
  }

  @Override
  public List<StatementWhenBranch> branches() {
    return branches;
  }

  @Override
  public StatementWhenBranch defaultBranch() {
    return defaultBranch;
  }
}
