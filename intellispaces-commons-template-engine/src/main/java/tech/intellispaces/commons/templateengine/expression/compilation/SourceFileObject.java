package tech.intellispaces.commons.templateengine.expression.compilation;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.Charset;

public final class SourceFileObject extends SimpleJavaFileObject {
  private final String source;
  private final long lastModified;

  public SourceFileObject(String className, String source) {
    super(createUri(className), Kind.SOURCE);
    this.source = source;
    this.lastModified = System.currentTimeMillis();
  }

  private static URI createUri(String className) {
    return URI.create(className.replace('.', '/') + Kind.SOURCE.extension);
  }

  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return this.source;
  }

  public OutputStream openOutputStream() {
    throw new IllegalStateException();
  }

  public InputStream openInputStream() {
    return new ByteArrayInputStream(this.source.getBytes(Charset.defaultCharset()));
  }

  public Writer openWriter() {
    throw new IllegalStateException();
  }

  public Reader openReader(boolean ignoreEncodingErrors) {
    return new StringReader(this.source);
  }

  public long getLastModified() {
    return this.lastModified;
  }
}
