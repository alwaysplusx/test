package org.harmony.test.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ScriptRunner {

    private Connection connection;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private String delimiter = ";";
    private boolean fullLineDelimiter = false;

    public ScriptRunner(Connection connection) {
        this.connection = connection;
    }

    public void runScript(File script) throws IOException, SQLException {
        runScript(new BufferedReader(new InputStreamReader(new FileInputStream(script))));
    }

    public void runScript(Reader reader) throws SQLException {
        setAutoCommit();
        try {
            executeLineByLine(reader);
        } catch (Exception e) {
            rollback();
        }
    }

    private void rollback() throws SQLException {
        connection.rollback();
    }

    private void executeLineByLine(Reader reader) throws IOException, SQLException {
        StringBuilder sb = new StringBuilder();
        BufferedReader lineReader = new BufferedReader(reader);
        String line;
        while ((line = lineReader.readLine()) != null) {
            sb = handleLine(sb, line);
        }
    }

    private StringBuilder handleLine(StringBuilder command, String line)
            throws SQLException, UnsupportedEncodingException {
        String trimmedLine = line.trim();
        if (lineIsComment(trimmedLine)) {
            // println(trimmedLine);
        } else if (commandReadyToExecute(trimmedLine)) {
            command.append(line.substring(0, line.lastIndexOf(delimiter)));
            command.append(LINE_SEPARATOR);
            // println(command);
            executeStatement(command.toString());
            command.setLength(0);
        } else if (trimmedLine.length() > 0) {
            command.append(line);
            command.append(LINE_SEPARATOR);
        }
        return command;
    }

    private void executeStatement(String command) throws SQLException, UnsupportedEncodingException {
        Statement statement = connection.createStatement();
        statement.setEscapeProcessing(true);
        String sql = command;
        statement.execute(sql);
        statement.close();
    }

    private boolean lineIsComment(String trimmedLine) {
        return trimmedLine.startsWith("//") || trimmedLine.startsWith("--");
    }

    private boolean commandReadyToExecute(String trimmedLine) {
        return !fullLineDelimiter && trimmedLine.contains(delimiter)
                || fullLineDelimiter && trimmedLine.equals(delimiter);
    }

    private void setAutoCommit() throws SQLException {
        connection.setAutoCommit(false);
    }

}
