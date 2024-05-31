package tech.intellispaces.framework.templateengine.template.element;

import tech.intellispaces.framework.templateengine.template.source.position.Position;
import tech.intellispaces.framework.templateengine.template.expression.value.Value;

import java.util.Map;

class TextElementImpl extends AbstractElement implements TextElement {
  private final String text;

  TextElementImpl(Position position, String text) {
    super(position);
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
