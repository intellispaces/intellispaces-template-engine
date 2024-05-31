package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.expression.Expression;
import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

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
