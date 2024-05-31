package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.List;
import java.util.Map;

class StatementWhenImpl extends AbstractElement implements StatementWhen {
  private final List<StatementWhenBranch> branches;
  private final StatementWhenBranch defaultBranch;

  StatementWhenImpl(Position position, List<StatementWhenBranch> branches, StatementWhenBranch defaultBranch) {
    super(position);
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
