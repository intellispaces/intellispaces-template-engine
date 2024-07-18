package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.exception.ResolveTemplateException;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.List;
import java.util.Map;

class FormatStatementImpl extends AbstractElement implements StatementFormat {
  private final List<MarkerFormatType> types;
  private final List<TemplateElement> subElements;

  FormatStatementImpl(
      TemplateElementContext context, List<MarkerFormatType> types, List<TemplateElement> subElements
  ) {
    super(context);
    this.types = types;
    this.subElements = subElements;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.StatementFormat;
  }

  @Override
  public List<MarkerFormatType> types() {
    return types;
  }

  @Override
  public List<TemplateElement> subElements() {
    return subElements;
  }

  @Override
  public String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ElementFunctions.resolve(this, variables);
  }
}
