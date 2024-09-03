package intellispaces.common.templateengine.element;

import java.util.List;

public interface StatementWhen extends TemplateElement {

  List<StatementWhenBranch> branches();

  StatementWhenBranch defaultBranch();
}
