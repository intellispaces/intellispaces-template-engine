package intellispaces.templateengine.model.expression;

/**
 * Expression template element.
 */
public interface Expression /*extends TemplateElement*/ {

//  @Override
//  default TemplateElementType type() {
//    return TemplateElementTypes.Expression;
//  }

  Expression expression();

//  @Override
//  default String resolve(Map<String, Value> params) throws ResolveTemplateException {
//    return ResolveFunctions.resolve(this, params);
//  }
}
