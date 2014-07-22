package org.moon.test.ioc.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.moon.test.ioc.core.Resource;
import org.moon.test.ioc.core.util.ClassUtils;

public class ClassPathResource implements Resource {

	public static final String FOLDER_SEPARATOR = "/";
	private ClassLoader classLoader;
	private final String path;

	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null) ? classLoader : ClassUtils.getDefualtClassLoader();
	}

	@Override
	public boolean exists() {
		return resolveURL() != null;
	}

	@Override
	public String getFilename() {
		if (this.path == null)
			return null;
		int separatorIndex = this.path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? this.path.substring(separatorIndex + 1) : this.path);
	}

	@Override
	public File getFile() throws IOException {
		return new File(resolveURL().getFile());
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(getFile());
	}

	private URL resolveURL() {
		if (classLoader != null) {
			return classLoader.getResource(this.path);
		} else {
			return ClassLoader.getSystemResource(this.path);
		}
	}

}
