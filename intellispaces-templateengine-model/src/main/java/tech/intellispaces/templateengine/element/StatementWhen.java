package tech.intellispaces.templateengine.element;

import java.util.List;

public interface StatementWhen extends TemplateElement {

  List<StatementWhenBranch> branches();

  StatementWhenBranch defaultBranch();
}
