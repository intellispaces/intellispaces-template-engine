package tech.intellispaces.commons.templateengine.element;

import java.util.List;
import java.util.Objects;

public final class WhenStatementBuilder {
  private TemplateElementContext context;
  private List<StatementWhenBranch> branches;
  private StatementWhenBranch defaultBranch;

  WhenStatementBuilder() {}

  public WhenStatementBuilder context(TemplateElementContext context) {
    this.context = context;
    return this;
  }

  public WhenStatementBuilder branches(List<StatementWhenBranch> branches) {
    this.branches = branches;
    return this;
  }

  public WhenStatementBuilder defaultBranch(StatementWhenBranch defaultBranch) {
    this.defaultBranch = defaultBranch;
    return this;
  }

  public StatementWhen get() {
    validate();
    return new WhenStatementImpl(context, branches, defaultBranch);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(branches);
  }
}
