package tech.intellispacesframework.templateengine.template.element;

import tech.intellispacesframework.templateengine.template.expression.Expression;

/**
 * Marker <when>.
 */
public interface MarkerWhen extends TemplateElement {

  Expression condition();
}
