package intellispaces.templateengine.model.expression;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.model.value.Value;

import java.util.Map;

public interface Literal /*extends TemplateElement*/ {

//  @Override
//  default TemplateElementType type() {
//    return TemplateElementTypes.Literal;
//  }

//  @Override
//  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
//    return text();
//  };

  Value value();
}
