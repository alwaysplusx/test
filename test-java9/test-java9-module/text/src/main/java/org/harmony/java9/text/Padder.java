package org.harmony.java9.text;

public class Padder {

    public static String leftPad(String s, int w) {
        StringBuilder o = new StringBuilder();
        for (int i = w - s.length(); i >= 0; i--) {
            o.append(" ");
        }
        return o.append(s).toString();
    }

}
