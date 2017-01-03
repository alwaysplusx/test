package org.harmony.test.javaee.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.h2.tools.Server;

/**
 * @author wuxii@foxmail.com
 */
public class H2Console {

    public static void main(String[] args) throws SQLException {
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        if (!argList.contains("-trace")) {
            argList.add("-trace");
        }

        if (!argList.contains("-webPort")) {
            argList.add("-webPort");
            argList.add("8081");
        }
        Server server = org.h2.tools.Server.createWebServer(argList.toArray(new String[argList.size()]));
        server.start();
        System.out.println("h2 web conbsole start at: " + server.getURL());
    }

}
