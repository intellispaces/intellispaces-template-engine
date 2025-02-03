package tech.intellispaces.commons.templateengine.element;

import tech.intellispaces.commons.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.commons.templateengine.expression.Expression;
import tech.intellispaces.commons.templateengine.expression.value.Value;

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
