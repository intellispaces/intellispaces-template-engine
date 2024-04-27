package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

/**
 * Marker <else>.
 */
public interface MarkerElse extends TemplateElement {

  Expression condition();
}
