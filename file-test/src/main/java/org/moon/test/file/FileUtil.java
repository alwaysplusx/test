package org.moon.test.file;

import java.io.File;

public class FileUtil {

	public static boolean deleteDirectory(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			String[] list = file.list();
			for (String subDir : list) {
				deleteDirectory(dir + File.separator + subDir);
			}
		}
		return file.delete();
	}

}
