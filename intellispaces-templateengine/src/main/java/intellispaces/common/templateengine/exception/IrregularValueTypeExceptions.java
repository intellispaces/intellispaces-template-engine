package intellispaces.common.templateengine.exception;

import tech.intellispaces.entity.text.StringFunctions;

/**
 * Provider of the exception {@link IrregularValueTypeException}.
 */
public interface IrregularValueTypeExceptions {

  static IrregularValueTypeException withMessage(String message) {
    return new IrregularValueTypeException(message);
  }

  static IrregularValueTypeException withMessage(String template, Object... params) {
    return new IrregularValueTypeException(StringFunctions.resolveTemplate(template, params));
  }
}
