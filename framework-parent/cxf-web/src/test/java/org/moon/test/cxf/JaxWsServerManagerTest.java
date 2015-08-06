package org.moon.test.cxf;

import org.moon.test.cxf.JaxWsServerManager;
import org.moon.test.cxf.Simple;

public class JaxWsServerManagerTest {

    public static void main(String[] args) {
        JaxWsServerManager.getInstance().publish(Simple.class, "http://localhost:8080/test", new Simple() {
            @Override
            public String sayHi(String name) {
                System.out.println("name say hi");
                return "Hi " + name;
            }
        });
    }

}
