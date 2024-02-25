package intellispaces.templateengine.model.element;

import intellispaces.templateengine.exception.ResolveTemplateException;
import intellispaces.templateengine.function.resolve.ResolveFunctions;
import intellispaces.templateengine.object.element.TemplateElementTypes;
import intellispaces.templateengine.model.value.Value;

import java.util.List;
import java.util.Map;

public interface StatementWhen extends TemplateElement {

  @Override
  default TemplateElementType type() {
    return TemplateElementTypes.StatementWhen;
  }

  List<StatementWhenBranch> branches();

  StatementWhenBranch defaultBranch();

  @Override
  default String resolve(Map<String, Value> variables) throws ResolveTemplateException {
    return ResolveFunctions.resolve(this, variables);
  }
}
