package intellispaces.common.templateengine.element;

import intellispaces.common.templateengine.expression.value.Value;

import java.util.Map;

class TextElementImpl extends AbstractElement implements TextElement {
  private final String text;

  TextElementImpl(TemplateElementContext context, String text) {
    super(context);
    this.text = text;
  }

  @Override
  public TemplateElementType type() {
    return TemplateElementTypes.Text;
  }

  @Override
  public String text() {
    return text;
  }

  @Override
  public String resolve(Map<String, Value> variables) {
    return ElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return text;
  }
}
