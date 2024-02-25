package intellispaces.templateengine.object.element;

import intellispaces.templateengine.model.element.TemplateElementType;

/**
 * Template element types.
 */
public enum TemplateElementTypes implements TemplateElementType {

  Text,

  MarkerPrint,

  MarkerWhen,

  MarkerElse,

  MarkerEnd,

  MarkerForeach,

  MarkerFormat,

  MarkerSet,

  StatementWhen,

  StatementForeach,

  StatementFormat
}
