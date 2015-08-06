package org.moon.test.ioc.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Resource {

    boolean exists();

    String getFilename();

    File getFile() throws IOException;

    InputStream getInputStream() throws IOException;
}
