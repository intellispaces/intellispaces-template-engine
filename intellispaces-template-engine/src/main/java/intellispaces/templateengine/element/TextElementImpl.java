package intellispaces.templateengine.element;

import intellispaces.templateengine.expression.value.Value;
import intellispaces.templateengine.position.Position;

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
    return ResolveElementFunctions.resolve(this, variables);
  }

  @Override
  public String toString() {
    return text;
  }
}
