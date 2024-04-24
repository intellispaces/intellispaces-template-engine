package intellispaces.templateengine.template.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.template.expression.Expression;
import intellispaces.templateengine.template.expression.value.Value;
import intellispaces.templateengine.template.source.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return "{{print " + outputExpression.statement() + "}}";
  }
}
