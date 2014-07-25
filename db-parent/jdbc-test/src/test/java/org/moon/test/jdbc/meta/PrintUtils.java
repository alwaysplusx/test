package org.moon.test.jdbc.meta;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class PrintUtils {
	
	public static final int W = 20;
	public static final String B = "\n";
	public static final String S = "|";
	private static final int LEFT = 1;

	public static void printTableStruts(ResultSetMetaData tableMetaData) throws Exception{
		StringBuffer t = new StringBuffer();
		t.append(S).append(appendToLength("Name", W))
		 .append(S).append(appendToLength("Type", W))
		 .append(S).append(appendToLength("className", W))
		 .append(S).append(appendToLength("Size", W)).append(S);
		String hr = appendToLength("", t.length(), LEFT, "-");
		
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
	
	public static String appendToLength(String str, int length) {
		return appendToLength(str, length, LEFT);
	}

	public static String appendToLength(String str, int length, int left) {
		return appendToLength(str, length, left, " ");
	}

	public static String appendToLength(String str, int length, int left, String appendStr) {
		if (str.length() < length) {
			if (LEFT == left) {
				str = appendToLength(appendStr + str, length, left * -1, appendStr);
			} else {
				str = appendToLength(str + appendStr, length, left * -1, appendStr);
			}
		}
		return str;
	}

	public static void printResultSet(ResultSet set) throws SQLException {
		while (set.next()) {
			for (int i = 1;; i++) {
				try {
					System.out.println(set.getObject(i));
				} catch (Exception e) {
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
		String hr = appendToLength("-", t.length(), LEFT, "-");
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
