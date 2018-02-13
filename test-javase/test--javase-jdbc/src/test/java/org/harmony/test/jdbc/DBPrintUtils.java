package org.harmony.test.jdbc;

import static org.harmony.test.jdbc.Pad.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBPrintUtils {
    
    public static final int W = 20;
    public static final String B = "\n";
    public static final String S = "|";

    public static void printTableStruts(ResultSetMetaData tableMetaData) throws Exception{
        StringBuffer t = new StringBuffer();
        t.append(S).append(appendToLength("Name", W))
         .append(S).append(appendToLength("Type", W))
         .append(S).append(appendToLength("className", W))
         .append(S).append(appendToLength("Size", W)).append(S);
        String hr = appendToLength(new StringBuffer("-"), t.length(), Center, "-");
        
        StringBuffer o = new StringBuffer();
        o.append(hr).append(B).append(t.toString()).append(B).append(hr).append(B);
        for (int i = 1; i <= tableMetaData.getColumnCount(); i++) {
            o.append(S).append(appendToLength(tableMetaData.getColumnName(i), W))
             .append(S).append(appendToLength(tableMetaData.getColumnTypeName(i), W))
             .append(S).append(appendToLength(tableMetaData.getColumnClassName(i), W))
             .append(S).append(appendToLength(tableMetaData.getColumnDisplaySize(i) + "", W))
             .append(S).append(B);
        }
        System.out.println(o.append(hr).toString());
    }
    
    public static void printResultSet(ResultSet set) throws SQLException {
        while (set.next()) {
            for (int i = 1;; i++) {
                try {
                    System.out.print(set.getObject(i)+ "    ");
                } catch (Exception e) {
                    System.out.println();
                    break;
                }
            }
        }
    }
    
    public static void printRecord(ResultSet record) throws SQLException {
        ResultSetMetaData data = record.getMetaData();
        StringBuffer t = new StringBuffer();
        t.append(S);
        for (int i = 1; i <= data.getColumnCount(); i++) {
            t.append(appendToLength(data.getColumnName(i), W)).append(S);
        }
        StringBuffer o = new StringBuffer();
        String hr = appendToLength(new StringBuffer("-"), t.length(), Center, "-");
        o.append(hr).append(B).append(t).append(B).append(hr).append(B);
        while (record.next()) {
            o.append(S);
            for (int i = 1;; i++) {
                try {
                    o.append(appendToLength(record.getObject(i).toString(), W)).append(S);
                } catch (Exception e) {
                    o.append(B);
                    break;
                }
            }
        }
        o.append(hr);
        System.out.println(o.toString());
    }
    
}

class Pad {

    public static final int Center = -1;
    public static final int Left = 1;

    public static String appendToLength(String str, int length) {
        return appendToLength(str, length, Center);
    }

    public static String appendToLength(String str, int length, int align) {
        return appendToLength(new StringBuffer(str), length, align, " ");
    }

    public static String appendToLength(StringBuffer sb, int length, int align, String appendStr) {
        if (sb.length() < length) {
            if (Left == align) {
                return appendToLength(sb.append(appendStr), length, align * Center, appendStr);
            } else {
                return appendToLength(sb.insert(0, appendStr), length, align * Center, appendStr);
            }
        }
        return sb.toString();
    }
}