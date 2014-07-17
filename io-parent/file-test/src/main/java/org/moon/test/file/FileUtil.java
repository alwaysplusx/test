package org.moon.test.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static boolean deleteDirectory(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			String[] list = file.list();
			for (String subDir : list) {
				deleteDirectory(dir + File.separator + subDir);
			}
		}
		boolean done = file.delete();
		if(done){			
			log.info("Deleting file " + file.getAbsolutePath());
		}else {
			log.warn("Can't delete file " + file.getAbsolutePath());
		}
		return done;
	}

}
