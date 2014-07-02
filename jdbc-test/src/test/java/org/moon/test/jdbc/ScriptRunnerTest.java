package org.moon.test.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.ibatis.jdbc.ScriptRunner;

public class ScriptRunnerTest {

	public static void main(String[] args) throws Exception {
		ScriptRunner runner = new ScriptRunner(DBUtils.getInstance().getConnection());
		runner.runScript(new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/tables.sql"))));
	}

}
