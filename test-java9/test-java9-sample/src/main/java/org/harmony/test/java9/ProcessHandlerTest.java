package org.harmony.test.java9;

public class ProcessHandlerTest {
    public static void main(String[] args) {
        ProcessHandle current = ProcessHandle.current();
        System.out.println("current process pid " + current.pid());
        current.children().forEach(p -> {
            System.out.println("destroy process " + p.pid());
            p.destroy();
        });
        // current.destroy();
    }
}
