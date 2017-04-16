package org.harmony.test.java.java8.reduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ReduceTest {

    Stream<String> lines = null;

    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("src/main/resources/t.txt")).forEach(x -> {
            System.out.println(x);
        });
    }

    @Before
    public void before() throws IOException {
        lines = Files.lines(Paths.get("src/main/resources/t.txt"));
    }

    @Test
    public void testFindLongestLineLength() {
        lines.mapToInt(String::length)//
                .max()//
                .getAsInt();
    }

    @Test
    public void testFindLongestLine() {
        // Big files will take a long time and a lot of resources
        String longestLine = lines.sorted((x, y) -> {
            return y.length() - x.length();
        }).findFirst().get();
        System.out.println(longestLine);
    }

    @Test
    public void testFindLongestLineReduceWay() {
        String longestLine = lines.reduce((x, y) -> {
            return (x.length() > y.length()) ? x : y;
        }).get();
        System.out.println(longestLine);
    }

    @Test
    public void testFindLongestLineComparator() {
        String longestLine = lines.max(Comparator.comparingInt(String::length)).get();
        System.out.println(longestLine);
    }

    @After
    public void after() {
        lines.close();
    }
}
