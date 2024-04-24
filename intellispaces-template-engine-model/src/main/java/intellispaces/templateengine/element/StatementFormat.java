package intellispaces.templateengine.element;

import java.util.List;

/**
 * Statement <format>.
 */
public interface StatementFormat extends TemplateElement {

  List<MarkerFormatType> types();

  List<TemplateElement> subElements();
}
