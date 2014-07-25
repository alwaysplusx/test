package org.moon.test.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ScriptRunnerTest {

	public static void main(String[] args) throws Exception {
		ScriptRunner runner = new ScriptRunner(DBUtils.getInstance().getConnection());
		runner.runScript(new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/tables.sql"))));
		// org.moon.test.jdbc.ScriptRunner run = new
		// org.moon.test.jdbc.ScriptRunner(DBUtils.getInstance().getConnection());
		// run.runScript("src/main/resources/tables.sql");
	}

}
