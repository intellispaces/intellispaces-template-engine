package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.exception.ResolveTemplateException;
import tech.intellispacesframework.templateengine.template.expression.Expression;
import tech.intellispacesframework.templateengine.template.expression.value.Value;
import tech.intellispacesframework.templateengine.template.source.position.Position;

import java.util.Map;

class MarkerPrintImpl extends AbstractElement implements MarkerPrint {
  private final Expression outputExpression;

  MarkerPrintImpl(Position position, Expression outputExpression) {
    super(position);
    this.outputExpression = outputExpression;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.MarkerPrint;
  }

  @Override
  public Expression outputExpression() {
    return outputExpression;
  }

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{print " + outputExpression.statement() + "}}";
  }
}
