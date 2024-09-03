package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.exception.ResolveTemplateException;
import intellispaces.common.templateengine.expression.value.Value;

import java.util.List;
import java.util.Map;

class WhenStatementImpl extends AbstractElement implements StatementWhen {
  private final List<StatementWhenBranch> branches;
  private final StatementWhenBranch defaultBranch;

  WhenStatementImpl(
      TemplateElementContext context, List<StatementWhenBranch> branches, StatementWhenBranch defaultBranch
  ) {
    super(context);
    this.branches = branches;
    this.defaultBranch = defaultBranch;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.StatementWhen;
  }

  @Override
  public List<StatementWhenBranch> branches() {
    return branches;
  }

  @Override
  public StatementWhenBranch defaultBranch() {
    return defaultBranch;
  }

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ElementFunctions.resolve(this, variables);
  }
}
