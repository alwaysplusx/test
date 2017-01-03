package org.moon.test.ioc.core.util;

public class StringUtils {

    public static boolean isBlank(String nameAttr) {
        return (nameAttr != null) ? nameAttr.length() == 0 : true;
    }

}
