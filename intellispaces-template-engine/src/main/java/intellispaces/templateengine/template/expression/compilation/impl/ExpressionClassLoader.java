package intellispaces.templateengine.template.expression.compilation.impl;

import java.util.Iterator;
import java.util.List;

public class ExpressionClassLoader extends ClassLoader {
  private final List<CompiledFileObject> snippetFileObjects;

  public ExpressionClassLoader(List<CompiledFileObject> snippetFileObjects, ClassLoader parentClassLoader) {
    super(parentClassLoader);
    this.snippetFileObjects = snippetFileObjects;
  }

  @Override
  protected Class<?> findClass(String className) throws ClassNotFoundException {
    Iterator<CompiledFileObject> itr = snippetFileObjects.iterator();
    while (itr.hasNext()) {
      CompiledFileObject file = itr.next();
      if (file.getClassName().equals(className)) {
        itr.remove();
        byte[] bytes = file.getBytes();
        return super.defineClass(className, bytes, 0, bytes.length);
      }
    }
    return super.findClass(className);
  }
}
