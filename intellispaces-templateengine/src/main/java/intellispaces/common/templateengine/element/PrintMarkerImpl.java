package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.exception.ResolveTemplateException;
import intellispaces.common.templateengine.expression.Expression;
import intellispaces.common.templateengine.expression.value.Value;

import java.util.Map;

class PrintMarkerImpl extends AbstractElement implements MarkerPrint {
  private final Expression outputExpression;

  PrintMarkerImpl(TemplateElementContext context, Expression outputExpression) {
    super(context);
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
