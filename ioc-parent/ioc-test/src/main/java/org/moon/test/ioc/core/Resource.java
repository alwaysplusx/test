package org.moon.test.ioc.core;

import java.io.File;
import java.io.IOException;

public interface Resource {

	boolean exists();
	
	String getFilename();
	
	File getFile() throws IOException;
}
