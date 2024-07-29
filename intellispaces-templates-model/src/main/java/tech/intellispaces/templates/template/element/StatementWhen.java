package tech.intellispaces.templates.template.element;

import java.util.List;

public interface StatementWhen extends TemplateElement {

  List<StatementWhenBranch> branches();

  StatementWhenBranch defaultBranch();
}
