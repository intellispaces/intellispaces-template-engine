package intellispaces.templateengine.function.compile.impl;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.List;

public class ExpressionJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
  private final List<CompiledFileObject> compiledFiles;

  public ExpressionJavaFileManager(JavaFileManager fileManager) {
    super(fileManager);
    compiledFiles = new ArrayList<>();
  }

  @Override
  public JavaFileObject getJavaFileForOutput(
      Location location, String className, JavaFileObject.Kind kind, FileObject sibling
  ) {
    var file = new CompiledFileObject(className, kind);
    compiledFiles.add(file);
    return file;
  }

  public List<CompiledFileObject> getGeneratedOutputFiles() {
    return compiledFiles;
  }
}
