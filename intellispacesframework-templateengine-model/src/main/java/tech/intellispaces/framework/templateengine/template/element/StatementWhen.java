package tech.intellispaces.framework.templateengine.template.element;

import java.util.List;

public interface StatementWhen extends TemplateElement {

  List<StatementWhenBranch> branches();

  StatementWhenBranch defaultBranch();
}
