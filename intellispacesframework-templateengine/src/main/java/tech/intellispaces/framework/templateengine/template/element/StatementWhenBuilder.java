package tech.intellispaces.framework.templateengine.template.element;

import java.util.List;
import java.util.Objects;

public final class StatementWhenBuilder {
  private TemplateElementContext context;
  private List<StatementWhenBranch> branches;
  private StatementWhenBranch defaultBranch;

  public static StatementWhenBuilder get() {
    return new StatementWhenBuilder();
  }

  public StatementWhenBuilder context(TemplateElementContext context) {
    this.context = context;
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
    return new StatementWhenImpl(context, branches, defaultBranch);
  }

  private void validate() {
    Objects.requireNonNull(context);
    Objects.requireNonNull(branches);
  }

  private StatementWhenBuilder() {}
}
